package it.eduman.android.trackYourTVseries.thetvdb;

public class TvdbAPIException extends Exception {

	private static final long serialVersionUID = 9182991849861275149L;
	
	public TvdbAPIException (String message){
		super(message);
	}
	
	public TvdbAPIException (Exception cause){
		super(cause);
	}
	
	public TvdbAPIException (String message, Exception cause){
		super(message, cause);
	}

}
