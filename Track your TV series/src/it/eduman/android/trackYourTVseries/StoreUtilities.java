package it.eduman.android.trackYourTVseries;

import it.eduman.android.trackYourTVseries.commons.TrackYourTVseriesConstants;
import it.eduman.android.trackYourTVseries.core.DefaultUser;
import it.eduman.android.trackYourTVseries.core.User;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class StoreUtilities {
	
	public static String getUserJson(Context context){
		SharedPreferences preferences = 
				context.getSharedPreferences(TrackYourTVseriesConstants.PREFS_NAME, 0);
		return preferences.getString(TrackYourTVseriesConstants.USER_FOLLOWED_LIST,
				(new Gson()).toJson(DefaultUser.getInstance(context)));
	}
	
	public static User getUser (Context context){
		SharedPreferences preferences = 
				context.getSharedPreferences(TrackYourTVseriesConstants.PREFS_NAME, 0);
		Gson gson = new Gson();
		String userJson = preferences.getString(TrackYourTVseriesConstants.USER_FOLLOWED_LIST,null);
		User user;
		if (userJson == null)
			user = DefaultUser.getInstance(context);
		else 
			user = gson.fromJson(userJson, User.class);
		
		return user;
	}
	
	public static SharedPreferences getMySharedPreference(Context context){
		return context.getSharedPreferences(TrackYourTVseriesConstants.PREFS_NAME, 0);
	}
	
	public static SharedPreferences.Editor getMySharedPreferenceEditor (Context context){
		return context.getSharedPreferences(TrackYourTVseriesConstants.PREFS_NAME, 0).edit();
	}
	
	public static void saveUser(Context context, User user){
		SharedPreferences.Editor editor = StoreUtilities.getMySharedPreferenceEditor(context);
		editor.putString(TrackYourTVseriesConstants.USER_FOLLOWED_LIST, 
				(new Gson()).toJson(user));
		editor.commit();
	}

}
