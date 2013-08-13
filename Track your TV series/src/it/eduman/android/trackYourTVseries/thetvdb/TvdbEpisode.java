package it.eduman.android.trackYourTVseries.thetvdb;

public class TvdbEpisode {
	public static enum XmlTags {id, Combined_episodenumber, Combined_season,
		DVD_chapter, DVD_discid, DVD_episodenumber, DVD_season, Director,
		EpImgFlag, EpisodeName, EpisodeNumber, FirstAired,
		GuestStars, IMDB_ID, Language, Overview, ProductionCode,
		Rating, RatingCount, SeasonNumber, Writer, absolute_number,
		airsafter_season, airsbefore_episode, airsbefore_season,
		filename, lastupdated, seasonid, seriesid, thumb_added,
		thumb_height, thumb_width, tms_export }
	
	private String id = "";
	private String Combined_episodenumber = "";
	private String Combined_season = "";
	private String DVD_chapter = "";
	private String DVD_discid = "";
	private String DVD_episodenumber = "";
	private String DVD_season = "";
	private String Director = "";
	private String EpImgFlag = "";
	private String EpisodeName = "";
	private String EpisodeNumber = "";
	private String FirstAired = "";
	private String GuestStars = "";
	private String IMDB_ID = "";
	private String Language = "";
	private String Overview = "";
	private String ProductionCode = "";
	private String Rating = "";
	private String RatingCount = "";
	private String SeasonNumber = "";
	private String Writer = "";
	private String absolute_number = "";
	private String airsafter_season = "";
	private String airsbefore_episode = "";
	private String airsbefore_season = "";
	private String filename = "";
	private String lastupdated = "";
	private String seasonid = "";
	private String seriesid = "";
	private String thumb_added = "";
	private String thumb_height = "";
	private String thumb_width = "";
	private String tms_export = "";
	
	public TvdbEpisode (String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public TvdbEpisode setId(String id) {
		this.id = id;
		return this;
	}
	public String getCombined_episodenumber() {
		return Combined_episodenumber;
	}
	public TvdbEpisode setCombined_episodenumber(String combined_episodenumber) {
		Combined_episodenumber = combined_episodenumber;
		return this;
	}
	public String getCombined_season() {
		return Combined_season;
	}
	public TvdbEpisode setCombined_season(String combined_season) {
		Combined_season = combined_season;
		return this;
	}
	public String getDVD_chapter() {
		return DVD_chapter;
	}
	public TvdbEpisode setDVD_chapter(String DVD_chapter) {
		this.DVD_chapter = DVD_chapter;
		return this;
	}
	public String getDVD_discid() {
		return DVD_discid;
	}
	public TvdbEpisode setDVD_discid(String DVD_discid) {
		this.DVD_discid = DVD_discid;
		return this;
	}
	public String getDVD_episodenumber() {
		return DVD_episodenumber;
	}
	public TvdbEpisode setDVD_episodenumber(String DVD_episodenumber) {
		this.DVD_episodenumber = DVD_episodenumber;
		return this;
	}
	public String getDVD_season() {
		return DVD_season;
	}
	public TvdbEpisode setDVD_season(String DVD_season) {
		this.DVD_season = DVD_season;
		return this;
	}
	public String getDirector() {
		return Director;
	}
	public TvdbEpisode setDirector(String director) {
		Director = director;
		return this;
	}
	public String getEpImgFlag() {
		return EpImgFlag;
	}
	public TvdbEpisode setEpImgFlag(String epImgFlag) {
		EpImgFlag = epImgFlag;
		return this;
	}
	public String getEpisodeName() {
		return EpisodeName;
	}
	public TvdbEpisode setEpisodeName(String episodeName) {
		EpisodeName = episodeName;
		return this;
	}
	public String getEpisodeNumber() {
		return EpisodeNumber;
	}
	public TvdbEpisode setEpisodeNumber(String episodeNumber) {
		EpisodeNumber = episodeNumber;
		return this;
	}
	public String getFirstAired() {
		return FirstAired;
	}
	public TvdbEpisode setFirstAired(String firstAired) {
		FirstAired = firstAired;
		return this;
	}
	public String getGuestStars() {
		return GuestStars;
	}
	public TvdbEpisode setGuestStars(String guestStars) {
		GuestStars = guestStars;
		return this;
	}
	public String getIMDB_ID() {
		return IMDB_ID;
	}
	public TvdbEpisode setIMDB_ID(String IMDB_ID) {
		this.IMDB_ID = IMDB_ID;
		return this;
	}
	public String getLanguage() {
		return Language;
	}
	public TvdbEpisode setLanguage(String language) {
		Language = language;
		return this;
	}
	public String getOverview() {
		return Overview;
	}
	public TvdbEpisode setOverview(String overview) {
		Overview = overview;
		return this;
	}
	public String getProductionCode() {
		return ProductionCode;
	}
	public TvdbEpisode setProductionCode(String productionCode) {
		ProductionCode = productionCode;
		return this;
	}
	public String getRating() {
		return Rating;
	}
	public TvdbEpisode setRating(String rating) {
		Rating = rating;
		return this;
	}
	public String getRatingCount() {
		return RatingCount;
	}
	public TvdbEpisode setRatingCount(String ratingCount) {
		RatingCount = ratingCount;
		return this;
	}
	public String getSeasonNumber() {
		return SeasonNumber;
	}
	public TvdbEpisode setSeasonNumber(String seasonNumber) {
		SeasonNumber = seasonNumber;
		return this;
	}
	public String getWriter() {
		return Writer;
	}
	public TvdbEpisode setWriter(String writer) {
		Writer = writer;
		return this;
	}
	public String getAbsolute_number() {
		return absolute_number;
	}
	public TvdbEpisode setAbsolute_number(String absolute_number) {
		this.absolute_number = absolute_number;
		return this;
	}
	public String getAirsafter_season() {
		return airsafter_season;
	}
	public TvdbEpisode setAirsafter_season(String airsafter_season) {
		this.airsafter_season = airsafter_season;
		return this;
	}
	public String getAirsbefore_episode() {
		return airsbefore_episode;
	}
	public TvdbEpisode setAirsbefore_episode(String airsbefore_episode) {
		this.airsbefore_episode = airsbefore_episode;
		return this;
	}
	public String getAirsbefore_season() {
		return airsbefore_season;
	}
	public TvdbEpisode setAirsbefore_season(String airsbefore_season) {
		this.airsbefore_season = airsbefore_season;
		return this;
	}
	public String getFilename() {
		return filename;
	}
	public TvdbEpisode setFilename(String filename) {
		this.filename = filename;
		return this;
	}
	public String getLastupdated() {
		return lastupdated;
	}
	public TvdbEpisode setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
		return this;
	}
	public String getSeasonid() {
		return seasonid;
	}
	public TvdbEpisode setSeasonid(String seasonid) {
		this.seasonid = seasonid;
		return this;
	}
	public String getSeriesid() {
		return seriesid;
	}
	public TvdbEpisode setSeriesid(String seriesid) {
		this.seriesid = seriesid;
		return this;
	}
	public String getThumb_added() {
		return thumb_added;
	}
	public TvdbEpisode setThumb_added(String thumb_added) {
		this.thumb_added = thumb_added;
		return this;
	}
	public String getThumb_height() {
		return thumb_height;
	}
	public TvdbEpisode setThumb_height(String thumb_height) {
		this.thumb_height = thumb_height;
		return this;
	}
	public String getThumb_width() {
		return thumb_width;
	}
	public TvdbEpisode setThumb_width(String thumb_width) {
		this.thumb_width = thumb_width;
		return this;
	}
	public String getTms_export() {
		return tms_export;
	}
	public TvdbEpisode setTms_export(String tms_export) {
		this.tms_export = tms_export;
		return this;
	}
	
	
	
}
