package it.eduman.android.trackYourTVseries;

import it.eduman.android.commons.utilities.ActionTask;
import it.eduman.android.commons.utilities.ErrorUtilities;
import it.eduman.android.commons.utilities.SoftwareUtilities;
import it.eduman.android.trackYourTVseries.ViewHolder.SearchedViewHolder;
import it.eduman.android.trackYourTVseries.commons.TrackYourTVseriesImageLoader;
import it.eduman.android.trackYourTVseries.core.SeriesConverter;
import it.eduman.android.trackYourTVseries.core.User;
import it.eduman.android.trackYourTVseries.thetvdb.TvdbAPIException;
import it.eduman.android.trackYourTVseries.thetvdb.TvdbAPIImpl;
import it.eduman.android.trackYourTVseries.thetvdb.TvdbSerie;
import it.eduman.android.trackYourTVseries.thetvdb.TvdbXML;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class SearchSeriesSectionFragment extends MyFragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */	

	private View rootView = null;
	private LayoutInflater inflater;
	private DisplayImageOptions options;
	private EditText searchEditText = null;
	private ImageButton searchButton = null;
	private Button deleteButton = null;
	private HashMap<String, TvdbSerie> searchedResults = new HashMap<String, TvdbSerie>();


	public SearchSeriesSectionFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		rootView = inflater.inflate(R.layout.search_fragment_activity,
				container, false);

		Debug.setPrintDebugMSG(rootView.getContext());

		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_launcher)
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(false)
		.cacheOnDisc(false)
		//		.displayer(new RoundedBitmapDisplayer(20))
		.build();

		searchButton = (ImageButton) rootView.findViewById(R.id.searchFragment_search_button);
		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				search();
			}
		});

		searchEditText = (EditText) rootView.findViewById(R.id.searchFragment_search_editText);
		searchEditText.setText(R.string.nullString);
		searchEditText.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
						(keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on key press
					InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
					in.hideSoftInputFromWindow(searchEditText.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
					search();
					return true;
				}
				return false;
			}
		});
		searchEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				handleDeleteButton();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void afterTextChanged(Editable s) {}
		});

		deleteButton = (Button) rootView.findViewById(R.id.searchFragment_deleteButton);
		handleDeleteButton();
		deleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				searchEditText.setText(R.string.nullString);
			}
		});

		return rootView;
	}


	@Override
	public void onResume(){
		super.onResume();
		Debug.setPrintDebugMSG(rootView.getContext());
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null){
			Gson gson = new Gson();
			Type type = new TypeToken<HashMap<String, TvdbSerie>>(){}.getType();
			searchedResults = gson.fromJson(savedInstanceState.getString("previousShownResults"), type);
			loadListView(searchedResults);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Gson gson = new Gson();
		outState.putString("previousShownResults", gson.toJson(searchedResults));
	}


	@Override
	public void update(){
		//TODO
	}
	private void handleDeleteButton(){
		if (searchButton != null && searchEditText != null && 
				searchEditText.getText().toString().equals("")){
			deleteButton.setVisibility(View.INVISIBLE);
		} else if (searchButton != null && searchEditText != null) {
			deleteButton.setVisibility(View.VISIBLE);
		}
	}

	public void clearListView(){
		ListView listView = (ListView)rootView.findViewById(R.id.search_series_ListView);
		listView.setAdapter(null);
	}

	public void loadListView(HashMap<String, TvdbSerie> series){

		if (series != null){
			Gson gson = new Gson();
			String userJson = StoreUtilities.getUserJson(rootView.getContext());
			User user = gson.fromJson(userJson, User.class);
			SearchedItemAdapter adapter = new SearchedItemAdapter(user, series);
			adapter.notifyDataSetChanged();
			ListView listView = (ListView)rootView.findViewById(R.id.search_series_ListView);
			listView.setAdapter(adapter);
			listView.setClickable(true);
			listView.setItemsCanFocus(false);
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Open a new activity with TV serie's details
					String title = ((TextView) view.findViewById(R.id.searched_series_list_row_title)).getText().toString();
					String overview = ((TextView) view.findViewById(R.id.searched_series_list_row_overview)).getText().toString();
					String serieID = ((TextView) view.findViewById(R.id.searched_series_list_row_serieID)).getText().toString();
					SoftwareUtilities.MyInfoDialogFactory(rootView.getContext(), serieID + " - " + title + "\n" + overview);
				}
			});
		}
	}

	private void search(){
		if (searchEditText.getText().toString().equals("")){
			SoftwareUtilities.MyErrorDialogFactory(rootView.getContext(), 
					R.string.searchByTitleInsertError,
					false,
					new ActionTask() {

				@Override
				public void onPositiveResponse() {
					searchEditText.requestFocus();
					InputMethodManager imm = (InputMethodManager) rootView.getContext()
							.getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.showSoftInput(searchEditText, 0);
				}

				@Override
				public void onNegativeResponse() {}
			});
		} else {
			//TODO verificare connessione ad internet
			clearListView();
			new AsyncSearch(rootView).execute(searchEditText.getText().toString());
		}


		//Hide keyboard
		InputMethodManager imm = (InputMethodManager) rootView.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}


	class AsyncSearch extends AsyncTask<String, Void, HashMap<String, TvdbSerie>>{
		private View view;
		private ProgressBar progressBar;

		public AsyncSearch (View view){
			this.view = view;
			progressBar = (ProgressBar) view.findViewById(R.id.searchFragment_progressBar);	
		}

		@Override
		protected void onPreExecute(){
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected HashMap<String, TvdbSerie> doInBackground(String... title) {
			Looper.prepare();
			HashMap<String, TvdbSerie> tvdbSeries;

			try {
				String xml = "";
				TvdbAPIImpl tvdb = new TvdbAPIImpl(false);
				//				String language = view.getContext().getResources().getConfiguration().locale.getCountry();
				xml = tvdb.getSeriesByTitle(view.getContext(), title[0]);
				tvdbSeries = TvdbXML.unmarshalSearchedSeries(xml);


			} catch (TvdbAPIException e) {
				SoftwareUtilities.MyErrorDialogFactory(view.getContext(), e.getMessage());
				tvdbSeries = new HashMap<String,TvdbSerie>();
			}

			return tvdbSeries;

		}

		@Override
		protected void onPostExecute(HashMap<String, TvdbSerie> series) {
			searchedResults = series;
			if (series.size() > 0)
				loadListView(series);
			else {
				SoftwareUtilities.MyInfoDialogFactory(view.getContext(), 
						view.getContext().getResources().getString(R.string.searchedSerieNotFound));
			}
			progressBar.setVisibility(View.INVISIBLE);
		} 
	}

	public static class AsyncSaveSeason extends AsyncTask<String, Void, HashMap<String, TvdbSerie>>{
		private View view;
		private ProgressBar progressBar;
		private String seriesIDtoBeAdded = null;
		private SearchedViewHolder holder = null;

		public AsyncSaveSeason (View view, SearchedViewHolder holder){
			this.view = view;
			this.holder = holder;
			progressBar = (ProgressBar) view.findViewById(R.id.searchFragment_progressBar);	
		}

		@Override
		protected void onPreExecute(){
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected HashMap<String, TvdbSerie> doInBackground(String... seriesID) {
			Looper.prepare();
			HashMap<String, TvdbSerie> tvdbSeries;

			try {
				String xml = "";
				TvdbAPIImpl tvdb = new TvdbAPIImpl(false);
				xml = tvdb.getSeriesFullInformation(view.getContext(), seriesID[0]);
				tvdbSeries = TvdbXML.unmarshalSearchedSeries(xml);
				seriesIDtoBeAdded = seriesID[0];


			} catch (TvdbAPIException e) {
				SoftwareUtilities.MyErrorDialogFactory(view.getContext(), e.getMessage());
				tvdbSeries = new HashMap<String,TvdbSerie>();
			}

			return tvdbSeries;

		}

		@Override
		protected void onPostExecute(HashMap<String, TvdbSerie> series) {

			if (seriesIDtoBeAdded != null && 
					series != null && 
					series.containsKey(seriesIDtoBeAdded)){

				User user = StoreUtilities.getUser(view.getContext());
				TvdbSerie tvdb = series.get(seriesIDtoBeAdded);							
				user.getFollowedSeries().put(seriesIDtoBeAdded, SeriesConverter.toTVSerie(tvdb));
				StoreUtilities.saveUser(view.getContext(), user);
				String msg = String.format(
						view.getResources().getString(R.string.serieAddedInFollowingInfo), 
						holder.title.getText().toString(),seriesIDtoBeAdded);
				holder.button.setText(R.string.searchedSeriesListRow_followedButton);
				SoftwareUtilities.MyInfoDialogFactory(view.getContext(), msg);
				FollowedSeriesSectionFragment.isToBeUpdated = true; 


			} else {
				SoftwareUtilities.MyInfoDialogFactory(view.getContext(), 
						view.getContext().getResources().getString(R.string.unableToFollowSearchedSerie));
			}
			progressBar.setVisibility(View.INVISIBLE);
		} 
	}


	class SearchedItemAdapter extends BaseAdapter {

		private ImageLoadingListener searchedPosterFirstDisplayListener = 
				new SearchedPosterFirstDisplayListener();
		private TvdbSerie[] tvdbSeries;
		private HashMap<String, TvdbSerie> resultsHasMap;
		private User user;


		public SearchedItemAdapter (User user, HashMap<String, TvdbSerie> resultsHashMap){
			this.user = user;
			this.resultsHasMap = resultsHashMap;
			this.tvdbSeries = this.resultListToArray(resultsHashMap.values());
		}

		private TvdbSerie[] resultListToArray(Collection<TvdbSerie> results){
			TvdbSerie[] series;
			series = new TvdbSerie[results.size()];
			results.toArray(series);
			return series;
		}


		@Override
		public int getCount() {
			return tvdbSeries.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = convertView;
			final SearchedViewHolder holder;
			if (convertView == null) {
				view = inflater.inflate(R.layout.searched_series_list_row, parent, false);
				holder = new SearchedViewHolder();
				holder.serieID = (TextView) view.findViewById(R.id.searched_series_list_row_serieID);
				holder.title = (TextView) view.findViewById(R.id.searched_series_list_row_title);
				holder.overview = (TextView) view.findViewById(R.id.searched_series_list_row_overview);
				holder.image = (ImageView) view.findViewById(R.id.searched_series_list_row_image);
				holder.button = (Button) view.findViewById(R.id.searched_series_list_unfollowButton);
				holder.button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						User updatedUser = StoreUtilities.getUser(v.getContext());

						if (updatedUser != null && 
								updatedUser.getFollowedSeries() != null 
								&& resultsHasMap != null){
							String toBeAdded = holder.serieID.getText().toString();
							if (!updatedUser.getFollowedSeries().containsKey(toBeAdded) && 
									resultsHasMap.containsKey(toBeAdded)){
								//TODO check internet connession
								(new AsyncSaveSeason(rootView, holder)).execute(toBeAdded);

							} else {
								String msg = String.format(
										v.getResources().getString(R.string.serieAlreadyFollowedError), 
										holder.title.getText().toString(),toBeAdded);
								SoftwareUtilities.MyErrorDialogFactory(v.getContext(), msg);
							}

						}
					}
				});

				view.setTag(holder);
			} else {
				holder = (SearchedViewHolder) view.getTag();
			}
			if (tvdbSeries[position].getId() != null) {
				holder.serieID.setText(tvdbSeries[position].getId());
				if (user.getFollowedSeries().containsKey(tvdbSeries[position].getId())){
					holder.button.setText(view.getContext().getResources()
							.getString(R.string.searchedSeriesListRow_followedButton));
					//					holder.button.setEnabled(false);
				}
			}

			if (tvdbSeries[position].getSeriesName() != null)
				holder.title.setText(tvdbSeries[position].getSeriesName());
			if (tvdbSeries[position].getOverview() != null)
				holder.overview.setText(tvdbSeries[position].getOverview());

			String imageUrl = "";
			if (tvdbSeries[position].getPoster() != null)
				imageUrl = tvdbSeries[position].getPoster();
			else if (tvdbSeries[position].getBanner() != null)
				imageUrl = tvdbSeries[position].getBanner();
			else if (tvdbSeries[position].getFanart() != null)
				imageUrl = tvdbSeries[position].getFanart();

			try {
				TrackYourTVseriesImageLoader.imageLoader.displayImage(imageUrl, holder.image, options, searchedPosterFirstDisplayListener);
			} catch (Exception e){
				Log.e("", ErrorUtilities.getExceptionDetails(e));
			}
			return view;
		}
	}

	private static class SearchedPosterFirstDisplayListener extends SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}


}