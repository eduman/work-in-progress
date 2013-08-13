package it.eduman.android.trackYourTVseries;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
	public static class SearchedViewHolder {
			public TextView title;
			public TextView overview;
			public ImageView image;
			public Button button;
			public TextView serieID;
		}
	
	public static class FollowedViewHolder {
		public TextView title;
		public TextView comment;
		public ImageView image;
		public Button deleteButton;
		public TextView season;
		public TextView episode;
		public TextView serieID;
	}
}