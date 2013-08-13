package it.eduman.android.trackYourTVseries.core;

public class UnderViewingSeason {
	
	private int season;
	private int episode;
	
	public UnderViewingSeason(int season, int episode){
		this.season = season;
		this.episode = episode;
	}

	public int getSeason() {
		return season;
	}

	public UnderViewingSeason setSeason(int season) {
		this.season = season;
		return this;
	}

	public int getEpisode() {
		return episode;
	}

	public UnderViewingSeason setEpisode(int episode) {
		this.episode = episode;
		return this;
	}
	
	

}
