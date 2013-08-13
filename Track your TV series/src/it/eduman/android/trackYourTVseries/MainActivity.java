package it.eduman.android.trackYourTVseries;

import it.eduman.android.trackYourTVseries.commons.TrackYourTVseriesConstants;
import it.eduman.android.trackYourTVseries.commons.TrackYourTVseriesConstants.Config;
import it.eduman.android.trackYourTVseries.commons.TrackYourTVseriesImageLoader;

import java.util.Locale;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private MyFragment searchFragment = null;
	private MyFragment followedFragment = null;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		if (Config.DEVELOPER_MODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
		}

		super.onCreate(savedInstanceState);
		TrackYourTVseriesImageLoader.initImageLoader(this);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				this.getApplicationContext(),
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
		.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return MenuUtilities.myMenuFactory(this, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuUtilities.onMyOptionsItemSelected(
				this, item.getItemId());
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		this.showFragment(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		this.hideKeyboard();
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		this.showFragment(tab.getPosition());
	}

	private void hideKeyboard(){
//		if (this.searchFragment != null){
//			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//			imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),      
//					InputMethodManager.HIDE_NOT_ALWAYS);
			EditText searchEditText = (EditText) findViewById(R.id.searchFragment_search_editText);
			InputMethodManager imm = (InputMethodManager) 
					this.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(
					searchEditText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);		}
//	}

	private void showFragment(int position){
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(position);
		if (followedFragment != null && searchFragment != null) {
			switch (position) {
			case TrackYourTVseriesConstants.FOLLOWED_FRAGMENT_POSITION:	
				followedFragment.update();
				break;
			case TrackYourTVseriesConstants.SEARCH_FRAGMENT_POSITION:	
				break;
			}
		}

	}




	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		private Context context;

		public SectionsPagerAdapter(Context context, FragmentManager fm) {
			super(fm);
			this.context = context;
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			MyFragment fragment = null;
			Bundle args = new Bundle();
			switch (position){
			case TrackYourTVseriesConstants.FOLLOWED_FRAGMENT_POSITION:
				fragment = followedFragment = new FollowedSeriesSectionFragment();
				//TODO
				args.putInt(TrackYourTVseriesConstants.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				break;
			case TrackYourTVseriesConstants.SEARCH_FRAGMENT_POSITION:
				fragment = searchFragment = new SearchSeriesSectionFragment();
				break;
			}

			return fragment;
		}

		@Override
		public int getCount() {
			return TrackYourTVseriesConstants.TOTAL_TAB_PAGES;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return this.context.getString(R.string.title_followed).toUpperCase(l);
			case 1:
				return this.context.getString(R.string.title_search).toUpperCase(l);
			}
			return null;
		}
	}

}
