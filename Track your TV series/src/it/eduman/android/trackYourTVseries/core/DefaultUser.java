package it.eduman.android.trackYourTVseries.core;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

public class DefaultUser {
	private static User user = null;
	
		
	public static User getInstance (Context context){
		if (user == null){
			AccountManager manager = AccountManager.get(context); 
			Account[] accounts = manager.getAccountsByType("com.google");
			user = new User(accounts[0].name);
		}
		return user;		
	}

}
