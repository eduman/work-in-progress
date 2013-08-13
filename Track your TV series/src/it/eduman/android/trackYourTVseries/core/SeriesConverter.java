package it.eduman.android.trackYourTVseries.core;

import it.eduman.android.trackYourTVseries.thetvdb.TvdbEpisode;
import it.eduman.android.trackYourTVseries.thetvdb.TvdbSerie;

import java.util.HashMap;
import java.util.Map.Entry;

public class SeriesConverter {
	
	public static TVSerie toTVSerie(TvdbSerie tvdb){
		
		String posterURL = "";
		
		if (tvdb.getPoster() != null){
			posterURL = tvdb.getPoster();
		} else if (tvdb.getBanner() != null){
			posterURL = tvdb.getBanner();
		} else if (tvdb.getFanart() != null){
			posterURL = tvdb.getFanart();
		}
		
		HashMap<String, Integer> totSeasonsAndEpisondes = new HashMap<String, Integer>();
		for (Entry<String, HashMap<String, TvdbEpisode>> map : tvdb.getAllEpisodes().entrySet()){
			totSeasonsAndEpisondes.put(
					map.getKey(), 
					map.getValue().values().size());
		}
		
		TVSerie result = new TVSerie.Builder(tvdb.getId(), posterURL)
			.setTitle(tvdb.getSeriesName())
			.setImdbID(tvdb.getIMDB_ID())
			.setLanguage(tvdb.getLanguage())
			.setZap2itID(tvdb.getZap2it_id())
			.setTotSeasonsAndEpisondes(totSeasonsAndEpisondes)
			.build();
		
		
		if (result.getTotSeasons()>= 1)
			result.setViewingSeason(1);
		else 
			result.setViewingSeason(0);
		
		if (result.getTotSeasonsAndEpisondes().containsKey(String.valueOf((int)1))){
			if (result.getTotSeasonsAndEpisondes().get(String.valueOf((int)1)) >= 1)
				result.setViewingEpisode(1);
			else 
				result.setViewingEpisode(0);
		} else 
			result.setViewingEpisode(0);
		

		return result;
		
	}
}
