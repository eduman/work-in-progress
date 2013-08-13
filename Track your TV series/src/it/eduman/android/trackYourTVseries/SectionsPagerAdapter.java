//package it.eduman.android.trackYourTVseries;
//
//import java.util.Locale;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//
///**
// * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
// * one of the sections/tabs/pages.
// */
//public class SectionsPagerAdapter extends FragmentPagerAdapter {
//	
//	private Context context;
//
//	public SectionsPagerAdapter(Context context, FragmentManager fm) {
//		super(fm);
//		this.context = context;
//	}
//
//	@Override
//	public Fragment getItem(int position) {
//		// getItem is called to instantiate the fragment for the given page.
//		// Return a DummySectionFragment (defined as a static inner class
//		// below) with the page number as its lone argument.
//		Fragment fragment = null;
//		Bundle args = new Bundle();
//		switch (position){
//			case Constants.FOLLOWED_FRAGMENT_POSITION:
//				fragment = new FollowedSeriesSectionFragment();
//				args.putInt(Constants.ARG_SECTION_NUMBER, position + 1);
//				fragment.setArguments(args);
//				break;
//			case Constants.SEARCH_FRAGMENT_POSITION:
//				fragment = new SearchSeriesSectionFragment();
//				break;
//		}
//		
//		return fragment;
//	}
//
//	@Override
//	public int getCount() {
//		// Show TABS_NUMBER total pages.
//		return Constants.TOTAL_TAB_PAGES;
//	}
//
//	@Override
//	public CharSequence getPageTitle(int position) {
//		Locale l = Locale.getDefault();
//		switch (position) {
//		case 0:
//			return this.context.getString(R.string.title_followed).toUpperCase(l);
//		case 1:
//			return this.context.getString(R.string.title_search).toUpperCase(l);
//		}
//		return null;
//	}
//}