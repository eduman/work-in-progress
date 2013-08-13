package it.eduman.android.trackYourTVseries;

import it.eduman.android.commons.utilities.ActionTask;
import it.eduman.android.commons.utilities.ErrorUtilities;
import it.eduman.android.commons.utilities.SoftwareUtilities;
import it.eduman.android.trackYourTVseries.ViewHolder.FollowedViewHolder;
import it.eduman.android.trackYourTVseries.commons.TrackYourTVseriesImageLoader;
import it.eduman.android.trackYourTVseries.core.TVSerie;
import it.eduman.android.trackYourTVseries.core.User;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class FollowedSeriesSectionFragment extends MyFragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */

	private View rootView = null;
	private DisplayImageOptions options;
	private LayoutInflater inflater;
	private User user;
	public static boolean isToBeUpdated = false;


	public FollowedSeriesSectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		rootView = inflater.inflate(R.layout.followed_series_fragment_activity,
				container, false);

		Debug.setPrintDebugMSG(rootView.getContext());

		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_launcher)
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_error)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		//.displayer(new RoundedBitmapDisplayer(20))
		.build();


		this.loadListView();
		return rootView;
	}

	@Override
	public void onResume(){
		super.onResume();
		Debug.setPrintDebugMSG(rootView.getContext());
		if (isToBeUpdated){
			this.loadListView();
		}
	}

	@Override
	public void update(){
		//update the listview only if something has been changed
		if (isToBeUpdated){
			this.loadListView();
		}
	}

	public void loadListView(){
		ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.followed_series_progressBar);
		progressBar.setVisibility(View.VISIBLE);

		Gson gson = new Gson();
		String userJson = StoreUtilities.getUserJson(rootView.getContext());
		
		user = gson.fromJson(userJson, User.class);

		if (user != null && user.getFollowedSeries() != null){
			FollowedItemAdapter adapter = new FollowedItemAdapter(user);
			ListView listView = (ListView)rootView.findViewById(R.id.followed_series_ListView);
			adapter.notifyDataSetChanged();
			listView.setAdapter(adapter);
			listView.setClickable(true);
			listView.setItemsCanFocus(false);
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Open a new activity with TV serie's details
					String toBeOpend = 
							((TextView) view.findViewById(R.id.followed_series_list_row_serieID)).getText().toString();
					if (user.getFollowedSeries().containsKey(toBeOpend)){
						SoftwareUtilities.MyInfoDialogFactory(view.getContext(), 
								(new Gson()).toJson(user.getFollowedSeries().get(toBeOpend)));
					}
					
				}
			});
		}

		progressBar.setVisibility(View.INVISIBLE);

	}


	class FollowedItemAdapter extends BaseAdapter {

		private ImageLoadingListener followedPosterFirstDisplayListener = 
				new FollowedPosterFirstDisplayListener();
		private TVSerie[] series;
		private User user;

		public FollowedItemAdapter (User user){
			this.user = user;
			this.series = this.seriesListToArray(this.user);

		}

		private TVSerie[] seriesListToArray(User user){
			TVSerie[] series;
			List<TVSerie> seriesList = user.sortFollowedSeriesList();
			series = new TVSerie[seriesList.size()];
			seriesList.toArray(series);
			return series;
		}

		@Override
		public int getCount() {
			return series.length;
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
			final FollowedViewHolder holder;
			if (convertView == null) {
				view = inflater.inflate(R.layout.followed_series_list_row, parent, false);
				holder = new FollowedViewHolder();
				holder.serieID = (TextView) view.findViewById(R.id.followed_series_list_row_serieID);
				holder.title = (TextView) view.findViewById(R.id.followed_series_list_row_title);
				holder.comment = (TextView) view.findViewById(R.id.followed_series_list_row_comment);
				holder.image = (ImageView) view.findViewById(R.id.followed_series_list_row_image);
				holder.deleteButton = (Button) view.findViewById(R.id.followed_series_list_unfollowButton);
				holder.deleteButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						SoftwareUtilities.MyInfoDialogFactory(
								v.getContext(), R.string.unfollowFollowedSerie, true, new ActionTask() {

									@Override
									public void onPositiveResponse() {
										if (user != null && user.getFollowedSeries() != null){
											String toBeRemoved = holder.serieID.getText().toString();
											user.getFollowedSeries()
												.remove(toBeRemoved);
											series = seriesListToArray(user);

											StoreUtilities.saveUser(rootView.getContext(), user);
											loadListView();
										}
									}

									@Override
									public void onNegativeResponse() {
										// Nothing to do
									}
									
									@Override
									public void onNeutralResponse() {
										// Nothing to do
									}
								});
					}
				});

				holder.season = (TextView) view.findViewById(R.id.followed_series_list_row_season);
				holder.episode = (TextView) view.findViewById(R.id.followed_series_list_row_episode);
				view.setTag(holder);
			} else {
				holder = (FollowedViewHolder) view.getTag();
			}

			holder.serieID.setText(series[position].getSeriesID());
			holder.title.setText(series[position].getTitle());
			holder.comment.setText(series[position].getComment());
			String text = view.getResources().getString(R.string.followedSeriesListRow_season) + "  ";
			text += String.valueOf(series[position].getViewingSeason());
			holder.season.setText(text);
			text = view.getResources().getString(R.string.followedSeriesListRow_episode) + " ";
			text += String.valueOf(series[position].getViewingEpisode());
			holder.episode.setText(text);

			try {
				TrackYourTVseriesImageLoader.imageLoader.displayImage(series[position].getPosterURL(), holder.image, options, followedPosterFirstDisplayListener);
			} catch (Exception e){
				Log.e("", ErrorUtilities.getExceptionDetails(e));
			}
			return view;
		}
	}




	private static class FollowedPosterFirstDisplayListener extends SimpleImageLoadingListener {

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