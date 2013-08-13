package it.eduman.android.trackYourTVseries.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class User {
	
	private String username;
	// everytime something is updated also lastUpdate must be updated.
	private Date lastUpdate;
	private HashMap<String, TVSerie> followedSeries = new HashMap<String, TVSerie>();
	
	public User(String username){
		this.username = username;
		this.lastUpdate = new Date();
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		this.lastUpdate = new Date();
		return this;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public HashMap<String, TVSerie> getFollowedSeries() {
		return followedSeries;
	}

	public User setFollowedSeries( HashMap<String, TVSerie> followedSeries) {
		this.followedSeries = followedSeries;
		this.lastUpdate = new Date();
		return this;
	}
	
	public User addFollowedSerie (TVSerie serie){
		this.followedSeries.put(serie.getSeriesID(), serie);
		this.lastUpdate = new Date();
		return this;
	}
	
	public List<TVSerie> sortFollowedSeriesList(){
		List<TVSerie> sortedList = new ArrayList<TVSerie>(this.followedSeries.values());
		TVSerieComparator com = new TVSerieComparator();
		Collections.sort(sortedList, com);
		return sortedList;
	}

	
	@Override
	public String toString() {
		
		StringBuilder series = new StringBuilder();
		for (TVSerie s : this.followedSeries.values()){
			series.append(s.toString() + ", ");
		}
		
		return "User [username=" + username + ", followedSeries={"
				+ series.toString() + "}]";
	}
	
	

}
