package it.eduman.android.trackYourTVseries.thetvdb;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import it.eduman.android.commons.utilities.SoftwareUtilities;
import android.content.Context;



public class TvdbAPIImpl implements TvdbAPI{
	 
//	private static String baseUrl = "http://thetvdb.com/";
//	private static final String APIKEY = "5D00CE1CF3B4A9FF";
	private boolean isApiKeyEnabled = false;
	
	public TvdbAPIImpl(boolean isApiKeyEnabled){
		this.isApiKeyEnabled = isApiKeyEnabled;
	}

	public boolean isApiKeyEnabled() {
		return isApiKeyEnabled;
	}

	public TvdbAPIImpl setApiKeyEnabled(boolean isApiKeyEnabled) {
		this.isApiKeyEnabled = isApiKeyEnabled;
		return this;
	}
	
	private String urlEncoder(String toBeEncoded) throws TvdbAPIException {
		try {
			return URLEncoder.encode(toBeEncoded, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new TvdbAPIException(e);
		}
	}

	@Override
	public String getSeriesByTitle(Context context, String title) throws TvdbAPIException {
		String uri = TvdbConstants.baseUrl + "api/GetSeries.php?seriesname=" + this.urlEncoder(title);
		SoftwareUtilities.shortDebugToast(context, uri);
		return HttpConnection.sendGet(uri);
	}
	
	@Override
	public String getSeriesByTitle (Context context, String title, String language) throws TvdbAPIException {
		String uri = TvdbConstants.baseUrl + "api/GetSeries.php?seriesname=" + this.urlEncoder(title) +
				"&language=" + this.urlEncoder(language);
		SoftwareUtilities.shortDebugToast(context, uri);
		return HttpConnection.sendGet(uri);
	}

	@Override
	public String getSeriesByRemoteID(Context context, String imdbID) throws TvdbAPIException {
		String uri = TvdbConstants.baseUrl + "api/GetSeriesByRemoteID.php?" +
				"imdbid=" + this.urlEncoder(imdbID);
		SoftwareUtilities.shortDebugToast(context, uri);
		return HttpConnection.sendGet(uri);
	}

	@Override
	public String getSeriesByRemoteID(Context context, String imdbID, 
			String language) throws TvdbAPIException {
		String uri = TvdbConstants.baseUrl + "api/GetSeriesByRemoteID.php?" +
				"imdbid=" + this.urlEncoder(imdbID) + 
				"&language=" + this.urlEncoder(language);
		SoftwareUtilities.shortDebugToast(context, uri);
		return HttpConnection.sendGet(uri);
	}

	@Override
	public String getSeriesByRemoteID(Context context, String imdbID, String language,
			String zap2itid) throws TvdbAPIException {
		String uri = TvdbConstants.baseUrl + "api/GetSeriesByRemoteID.php?" +
				"imdbid=" + this.urlEncoder(imdbID) + 
				"&language=" + this.urlEncoder(language) + 
				"&zap2it=" + this.urlEncoder(zap2itid);
		SoftwareUtilities.shortDebugToast(context, uri);
		return HttpConnection.sendGet(uri);
	}

	@Override
	public String getEpisodeByAirDate(Context context, String seriesID, String airdate) 
			throws TvdbAPIException {
		String uri = TvdbConstants.baseUrl + "api/GetEpisodeByAirDate.php?" +
				"apikey=" + TvdbConstants.APIKEY + 
				"&seriesid=" + this.urlEncoder(seriesID) + 
				"&airdate=" + this.urlEncoder(airdate);
		SoftwareUtilities.shortDebugToast(context, uri);
		return HttpConnection.sendGet(uri);
	}

	@Override
	public String getEpisodeByAirDate(Context context, String seriesID, 
			String airdate, String language) throws TvdbAPIException {
		
		String uri = TvdbConstants.baseUrl + "api/GetEpisodeByAirDate.php?" +
				"apikey=" + TvdbConstants.APIKEY + 
				"&seriesid=" + this.urlEncoder(seriesID) + 
				"&airdate=" + this.urlEncoder(airdate) + 
				"&language=" + this.urlEncoder(language);
		SoftwareUtilities.shortDebugToast(context, uri);
		return HttpConnection.sendGet(uri);
	}

	@Override
	public String getSeriesBaseInformation(Context context, String seriesID) throws TvdbAPIException {
		String uri = "";
		if (this.isApiKeyEnabled){
			uri = TvdbConstants.baseUrl + "api/" + TvdbConstants.APIKEY + "/" + this.urlEncoder(seriesID) + "/";
		} else {
			uri = TvdbConstants.baseUrl + "data/series/" + this.urlEncoder(seriesID) + "/";
		}
		SoftwareUtilities.shortDebugToast(context, uri);
		return HttpConnection.sendGet(uri);
	}

	@Override
	public String getSeriesFullInformation(Context context, String seriesID) throws TvdbAPIException {
		String uri = "";
		if (this.isApiKeyEnabled){
			uri = TvdbConstants.baseUrl + "api/" + TvdbConstants.APIKEY + "/" + this.urlEncoder(seriesID) + "/all/";
		} else {
			uri = TvdbConstants.baseUrl + "data/series/" + this.urlEncoder(seriesID) + "/all/";
		}
		SoftwareUtilities.shortDebugToast(context, uri);
		return HttpConnection.sendGet(uri);
	}

	@Override
	public String getCurrentServerTime(Context context) throws TvdbAPIException {
		String uri = TvdbConstants.baseUrl + "api/Updates.php?type=none";
		SoftwareUtilities.shortDebugToast(context, uri);
		return HttpConnection.sendGet(uri);
	}

}
