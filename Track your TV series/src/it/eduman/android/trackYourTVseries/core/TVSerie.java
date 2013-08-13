package it.eduman.android.trackYourTVseries.core;

import java.util.HashMap;

public class TVSerie {	
	public static class Builder{
		private TVSerie tvSerie;
		public Builder (String id, String posterURL){
			this.tvSerie = new TVSerie(id, "", "", posterURL, "", "en", ""); 
		}
		
		public TVSerie build (){
			return tvSerie;
		}
		
		public Builder setTitle (String title){
			this.tvSerie.setTitle(title);
			return this;
		}
		
		public Builder setImdbID (String imdbID){
			this.tvSerie.setImdbID(imdbID);
			return this;
		}
		
		public Builder setLanguage (String language){
			this.tvSerie.setLanguage(language);
			return this;
		}
		
		public Builder setZap2itID (String zap2itID){
			this.tvSerie.setZap2itID(zap2itID);
			return this;
		}
		
		public Builder setTotSeasonsAndEpisondes(HashMap<String, Integer> totSeasonsAndEpisondes){
			this.tvSerie.setTotSeasonsAndEpisondes(totSeasonsAndEpisondes);
			return this;
		}
		
		public Builder setViewingSeason(int season){
			this.tvSerie.setViewingSeason(season);
			return this;
		}
		
		public Builder setViewingEpisode(int episode){
			this.tvSerie.setViewingEpisode(episode);
			return this;
		}

	}
	
	
	private String seriesID;
	private String title;
	private String comment;
	private String posterURL;
	private String imdbID;
	private String language;
	private String zap2itID;
	private HashMap<String, Integer> totSeasonsAndEpisondes;
	private int totSeasons;
	private UnderViewingSeason currentSeason;
	
	private TVSerie(String seriesID, 
			String title, 
			String comment, 
			String posterURL, 
			String imdbID, 
			String language, 
			String zap2itID) {
		this.seriesID = seriesID;
		this.title = title;
		this.comment = comment;
		this.posterURL = posterURL;
		this.imdbID = imdbID;
		this.language = language;
		this.zap2itID = zap2itID;
		currentSeason = new UnderViewingSeason(1, 1);
		this.totSeasonsAndEpisondes = new HashMap<String, Integer>();
		this.totSeasons = this.totSeasonsAndEpisondes.size();
	}

	public String getSeriesID() {
		return seriesID;
	}

	public TVSerie setSeriesID(String seriesID) {
		this.seriesID = seriesID;
		return this;
	}

	public String getImdbID() {
		return imdbID;
	}

	public TVSerie setImdbID(String imdbID) {
		this.imdbID = imdbID;
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public TVSerie setLanguage(String language) {
		this.language = language;
		return this;
	}

	public String getZap2itID() {
		return zap2itID;
	}

	public TVSerie setZap2itID(String zap2itID) {
		this.zap2itID = zap2itID;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public TVSerie setTitle(String title) {
		this.title = title;
		return this;
	}


	public String getComment() {
		return comment;
	}


	public TVSerie setComment(String comment) {
		this.comment = comment;
		return this;
	}


	public String getPosterURL() {
		return posterURL;
	}


	public TVSerie setPosterURL(String posterURL) {
		this.posterURL = posterURL;
		return this;
	}


	public int getTotSeasons() {
		return totSeasons;
	}


//	public TVSerie setTotSeasons(int totSeasons) {
//		this.totSeasons = totSeasons;
//		return this;
//	}
	
	public int getViewingSeason(){
		return this.currentSeason.getSeason();
	}
	
	public TVSerie setViewingSeason (int season){
		this.currentSeason.setSeason(season);
		return this;
	}
	
	public int getViewingEpisode(){
		return this.currentSeason.getEpisode();
	}
	
	public TVSerie setViewingEpisode(int episode){
		this.currentSeason.setEpisode(episode);
		return this;
	}
	
	

//	public UnderViewingSeason getCurrentSeason() {
//		return currentSeason;
//	}
//
//	public TVSerie setCurrentSeason(UnderViewingSeason currentSeason) {
//		this.currentSeason = currentSeason;
//		return this;
//	}


	public HashMap<String, Integer> getTotSeasonsAndEpisondes() {
		return totSeasonsAndEpisondes;
	}

	public TVSerie setTotSeasonsAndEpisondes(HashMap<String, Integer> totSeasonsAndEpisondes) {
		this.totSeasonsAndEpisondes = totSeasonsAndEpisondes;
		this.totSeasons = this.totSeasonsAndEpisondes.size();
		return this;
	}
	
	public TVSerie incrementSeasonLenght(String seasonNumber){
		if (this.totSeasonsAndEpisondes.containsKey(seasonNumber)){
			int totEpisode = this.totSeasonsAndEpisondes.get(seasonNumber);
			totEpisode++;
			this.totSeasonsAndEpisondes.put(seasonNumber, totEpisode);
		} else {
			this.totSeasonsAndEpisondes.put(seasonNumber, 1);
			this.totSeasons++;
		}
		return this;
	}

	@Override
	public String toString() {
		return "TVSerie [id=" + seriesID + ", title=" + title + ", comment="
				+ comment + ", posterURL=" + posterURL + ", totSeasons="
				+ totSeasons + ", currentSeason=" + currentSeason + "]";
	}	
	
	
	
	
}