package it.eduman.android.trackYourTVseries;

import it.eduman.android.commons.utilities.ActionTask;
import it.eduman.android.commons.utilities.SoftwareUtilities;
import it.eduman.android.trackYourTVseries.commons.TrackYourTVseriesImageLoader;
import it.eduman.android.trackYourTVseries.core.User;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class SettingsActivity extends PreferenceActivity{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
	}

	public static class SettingsFragment extends PreferenceFragment {
		private Context context;
		@Override
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.settings_menu);
			
			this.context = this.getActivity();

			Preference wifi = 
					(Preference) findPreference(getResources().getString(
							R.string.preference_wifiSettings_key));
			wifi.setOnPreferenceClickListener(new OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference) {
					startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
					return true;
				}
			});
			
			Preference mobile = 
					(Preference) findPreference(getResources().getString(
							R.string.preference_data_roamingSettings_key));
			mobile.setOnPreferenceClickListener(new OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference) {
					startActivity(new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS));
					return true;
				}
			});
			
			Preference clearUserData = (Preference) findPreference(getResources().getString(
					R.string.preference_user_data_cache_key));
			clearUserData.setOnPreferenceClickListener(new OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference) {
					SoftwareUtilities.MyInfoDialogFactory(
							context, R.string.preference_clearUserCacheMsg, true, new ActionTask() {

						@Override
						public void onPositiveResponse() {
							User user = StoreUtilities.getUser(context);
							user.cleare();
							StoreUtilities.saveUser(context, user);
							FollowedSeriesSectionFragment.isToBeUpdated = true;
							SoftwareUtilities.shortToast(context, R.string.preference_clearedUserCacheMsg);

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
					return true;
				}
			});
			
			
			Preference clearImageCache = 
					(Preference) findPreference(getResources().getString(
							R.string.preference_clear_image_cache_key));
			clearImageCache.setOnPreferenceClickListener(new OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference) {
					SoftwareUtilities.MyInfoDialogFactory(
							context, R.string.preference_clearImageCacheMsg, true, new ActionTask() {

						@Override
						public void onPositiveResponse() {
							TrackYourTVseriesImageLoader.imageLoader.clearMemoryCache();
							TrackYourTVseriesImageLoader.imageLoader.clearDiscCache();
							SoftwareUtilities.shortToast(context, R.string.preference_clearedImageCacheMsg);

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
					return true;
				}
			});
			
			

//			Preference clearMemoryCache = 
//					(Preference) findPreference(getResources().getString(
//							R.string.preference_clear_images_cache_key));
//			clearMemoryCache.setOnPreferenceClickListener(new OnPreferenceClickListener() {
//				@Override
//				public boolean onPreferenceClick(Preference preference) {
//					SoftwareUtilities.MyInfoDialogFactory(
//							context, R.string.preference_clearMemoryCacheMsg, true, new ActionTask() {
//
//						@Override
//						public void onPositiveResponse() {
//							TrackYourTVseriesImageLoader.imageLoader.clearMemoryCache();
//							SoftwareUtilities.shortToast(context, R.string.preference_clearedMemoryCacheMsg);
//
//						}
//
//						@Override
//						public void onNegativeResponse() {
//							// Nothing to do 
//						}
//						
//						@Override
//						public void onNeutralResponse() {
//							// Nothing to do
//						}
//					});
//					return true;
//				}
//			});
//
//			Preference clearDiscCache = 
//					(Preference) findPreference(getResources().getString(
//							R.string.preference_clear_disc_cache_key));
//			clearDiscCache.setOnPreferenceClickListener(new OnPreferenceClickListener() {
//				@Override
//				public boolean onPreferenceClick(Preference preference) {
//					SoftwareUtilities.MyInfoDialogFactory(
//							context, R.string.preference_clearDiscCacheMsg, true, new ActionTask() {
//
//						@Override
//						public void onPositiveResponse() {
//							TrackYourTVseriesImageLoader.imageLoader.clearDiscCache();
//							SoftwareUtilities.shortToast(context, R.string.preference_clearedDiscCacheMsg);
//						}
//
//						@Override
//						public void onNegativeResponse() {
//							// Nothing to do 
//						}
//						
//						@Override
//						public void onNeutralResponse() {
//							// Nothing to do
//						}
//					});
//					return true;
//				}
//			});
		}
	}

}
