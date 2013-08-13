package it.eduman.android.trackYourTVseries.commons;

public class TrackYourTVseriesException extends Exception {

	private static final long serialVersionUID = -5491388557644577003L;
	
	public TrackYourTVseriesException (String message){
		super (message);
	}
	
	public TrackYourTVseriesException (Exception cause){
		super (cause);
	}
	
	public TrackYourTVseriesException (String message, Exception cause){
		super (message, cause);
	}

}
