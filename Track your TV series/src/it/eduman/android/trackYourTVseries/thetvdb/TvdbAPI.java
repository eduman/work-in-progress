package it.eduman.android.trackYourTVseries.thetvdb;

import android.content.Context;

public interface TvdbAPI {
	public String getSeriesByTitle (Context context,String title) throws TvdbAPIException;
	public String getSeriesByTitle (Context context, String title, String language) throws TvdbAPIException;
	public String getSeriesByRemoteID (Context context, String imdbID) throws TvdbAPIException;
	public String getSeriesByRemoteID (Context context, String imdbID, String language) throws TvdbAPIException;
	public String getSeriesByRemoteID (Context context, String imdbID, String language, String zap2itid) throws TvdbAPIException;
	public String getEpisodeByAirDate (Context context, String seriesID, String airdate) throws TvdbAPIException;
	public String getEpisodeByAirDate (Context context, String seriesID, String airdate, String language) throws TvdbAPIException;
	
	public String getSeriesBaseInformation(Context context, String seriesID) throws TvdbAPIException;
	public String getSeriesFullInformation(Context context, String seriesID) throws TvdbAPIException;
	
	public String getCurrentServerTime(Context context) throws TvdbAPIException;

	

}
