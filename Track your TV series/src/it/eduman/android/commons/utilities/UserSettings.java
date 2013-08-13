//package it.eduman.android.commons.utilities;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//
//public class UserSettings {
//	private SharedPreferences sharedPref;
//	private Context context;
//	
//	public UserSettings(Context context){
//		this.context = context;
//		this.sharedPref = PreferenceManager.getDefaultSharedPreferences(context);	
//	}
//	
//	public String getSmartHomeAddress(){
//		return this.sharedPref.getString(Constants.ANDROID_PROXY_WEB_SERVICE_ADDRESS, null);
//	}
//	
//	public String getSmartHomePortNumber(){
//		return this.sharedPref.getString(Constants.ANDROID_PROXY_WEB_SERVICE_PORT_NUMBER, null);
//	}
//	
//	public String getNetworkManagerAddress(){
//		return this.sharedPref.getString(Constants.NETWORK_MANAGER_ADDRESS, null);
//	}
//	
//	public String getNetworkManagerPortNumber(){
//		return this.sharedPref.getString(Constants.NETWORK_MANAGER_PORT_NUMBER, null);
//	}
//
//}
