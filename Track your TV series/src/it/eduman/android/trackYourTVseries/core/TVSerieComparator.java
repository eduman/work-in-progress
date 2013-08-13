package it.eduman.android.trackYourTVseries.core;

import java.util.Comparator;

public class TVSerieComparator implements Comparator<TVSerie>{
	
	@Override
	public int compare(TVSerie lhs, TVSerie rhs) {
		return lhs.getTitle().compareToIgnoreCase(rhs.getTitle());
	}



}
