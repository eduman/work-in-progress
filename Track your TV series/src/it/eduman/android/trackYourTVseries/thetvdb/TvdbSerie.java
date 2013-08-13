package it.eduman.android.trackYourTVseries.thetvdb;

import java.util.HashMap;

public class TvdbSerie {
	
	public static enum XmlTags {id, Actors, Airs_DayOfWeek, Airs_Time, 
		ContentRating, FirstAired, Genre, IMDB_ID, language, Network, 
		NetworkID, Overview, Rating, RatingCount, Runtime, seriesID, 
		SeriesName, Status, added, addedBy, banner, fanart, lastupdated,
		poster, zap2it_id};
	
	private String id = "";
	private String Actors = "";
	private String Airs_DayOfWeek = "";
	private String Airs_Time = "";
	private String ContentRating = "";
	private String FirstAired = "";
	private String Genre = "";
	private String IMDB_ID = "";
	private String language = "";
	private String Network = "";
	private String NetworkID = "";
	private String Overview = "";
	private String Rating = "";
	private String RatingCount = "";
	private String Runtime = "";
	private String seriesID = "";
	private String SeriesName = "";
	private String Status = "";
	private String added = "";
	private String addedBy = "";
	private String banner = null;
	private String fanart = null;
	private String lastupdated = "";
	private String poster = null;
	private String zap2it_id = "";
//	private HashMap<String, Integer> totSeasonsAndEpisondes;
	private HashMap<String, HashMap<String, TvdbEpisode>> allEpisodes;
	
	
	public TvdbSerie (String id){
		this.id = id;
//		this.totSeasonsAndEpisondes = new HashMap<String, Integer>();
		this.allEpisodes = new HashMap<String, HashMap<String,TvdbEpisode>>();
	}
	
	
	
//	public TvdbSerie incrementSeasonLenght(String seasonNumber){
//		if (this.totSeasonsAndEpisondes.containsKey(seasonNumber)){
//			int totEpisode = this.totSeasonsAndEpisondes.get(seasonNumber);
//			totEpisode++;
//			this.totSeasonsAndEpisondes.put(seasonNumber, totEpisode);
//		} else {
//			this.totSeasonsAndEpisondes.put(seasonNumber, 1);
//		}
//		return this;
//	}
//
//	public HashMap<String, Integer> getTotSeasonsAndEpisondes() {
//		return totSeasonsAndEpisondes;
//	}
//
//	public void setTotSeasonsAndEpisondes(
//			HashMap<String, Integer> totSeasonsAndEpisondes) {
//		this.totSeasonsAndEpisondes = totSeasonsAndEpisondes;
//	}

	public HashMap<String, HashMap<String, TvdbEpisode>> getAllEpisodes() {
		return allEpisodes;
	}



	public void setAllEpisodes(
			HashMap<String, HashMap<String, TvdbEpisode>> allEpisodes) {
		this.allEpisodes = allEpisodes;
	}
	
	public void addEpisode(TvdbEpisode episode){
		if (this.allEpisodes.containsKey(episode.getSeasonNumber())){
			this.allEpisodes.get(episode.getSeasonNumber()).put(episode.getId(), episode);
		} else {
			HashMap<String, TvdbEpisode> episodesForSeason = 
					new HashMap<String, TvdbEpisode>();
			episodesForSeason.put(episode.getId(), episode);
			this.allEpisodes.put(episode.getSeasonNumber(), episodesForSeason);
			
 		}
	}



	public String getId() {
		return id;
	}

	public TvdbSerie setId(String id) {
		this.id = id;
		return this;
	}

	public String getActors() {
		return Actors;
	}

	public TvdbSerie setActors(String actors) {
		Actors = actors;
		return this;
	}

	public String getAirs_DayOfWeek() {
		return Airs_DayOfWeek;
	}

	public TvdbSerie setAirs_DayOfWeek(String airs_DayOfWeek) {
		Airs_DayOfWeek = airs_DayOfWeek;
		return this;
	}

	public String getAirs_Time() {
		return Airs_Time;
	}

	public TvdbSerie setAirs_Time(String airs_Time) {
		Airs_Time = airs_Time;
		return this;
	}

	public String getFirstAired() {
		return FirstAired;
	}

	public TvdbSerie setFirstAired(String firstAired) {
		FirstAired = firstAired;
		return this;
	}

	public String getGenre() {
		return Genre;
	}

	public TvdbSerie setGenre(String genre) {
		Genre = genre;
		return this;
	}

	public String getIMDB_ID() {
		return IMDB_ID;
	}

	public TvdbSerie setIMDB_ID(String iMDB_ID) {
		IMDB_ID = iMDB_ID;
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public TvdbSerie setLanguage(String language) {
		this.language = language;
		return this;
	}

	public String getNetwork() {
		return Network;
	}

	public TvdbSerie setNetwork(String network) {
		Network = network;
		return this;
	}

	public String getOverview() {
		return Overview;
	}

	public TvdbSerie setOverview(String overview) {
		Overview = overview;
		return this;
	}

	public String getRating() {
		return Rating;
	}

	public TvdbSerie setRating(String rating) {
		Rating = rating;
		return this;
	}

	public String getRatingCount() {
		return RatingCount;
	}

	public TvdbSerie setRatingCount(String ratingCount) {
		RatingCount = ratingCount;
		return this;
	}

	public String getRuntime() {
		return Runtime;
	}

	public TvdbSerie setRuntime(String runtime) {
		Runtime = runtime;
		return this;
	}

	public String getSeriesID() {
		return seriesID;
	}

	public TvdbSerie setSeriesID(String seriesID) {
		this.seriesID = seriesID;
		return this;
	}

	public String getSeriesName() {
		return SeriesName;
	}

	public TvdbSerie setSeriesName(String seriesName) {
		SeriesName = seriesName;
		return this;
	}

	public String getStatus() {
		return Status;
	}

	public TvdbSerie setStatus(String status) {
		Status = status;
		return this;
	}

	public String getBanner() {
		return banner;
	}

	public TvdbSerie setBanner(String banner) {
		this.banner = TvdbConstants.bannerUrl + banner;
		return this;
	}

	public String getFanart() {
		return fanart;
	}

	public TvdbSerie setFanart(String fanart) {
		this.fanart = TvdbConstants.fanartUrl + fanart;
		return this;
	}

	public String getPoster() {
		return poster;
	}

	public TvdbSerie setPoster(String poster) {
		this.poster =TvdbConstants.postertUrl + poster;
		return this;
	}

	public String getZap2it_id() {
		return zap2it_id;
	}

	public TvdbSerie setZap2it_id(String zap2it_id) {
		this.zap2it_id = zap2it_id;
		return this;
	}

	public String getContentRating() {
		return ContentRating;
	}

	public TvdbSerie setContentRating(String contentRating) {
		ContentRating = contentRating;
		return this;
	}

	public String getNetworkID() {
		return NetworkID;
	}

	public TvdbSerie setNetworkID(String networkID) {
		NetworkID = networkID;
		return this;
	}

	public String getAdded() {
		return added;
	}

	public TvdbSerie setAdded(String added) {
		this.added = added;
		return this;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public TvdbSerie setAddedBy(String addedBy) {
		this.addedBy = addedBy;
		return this;
	}

	public String getLastupdated() {
		return lastupdated;
	}

	public TvdbSerie setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
		return this;
	}
	
}
