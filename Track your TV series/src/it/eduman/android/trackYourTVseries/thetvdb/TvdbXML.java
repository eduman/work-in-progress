package it.eduman.android.trackYourTVseries.thetvdb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class TvdbXML {
// References:
// https://code.google.com/p/android-source-browsing/source/browse/samples/training/network-usage/src/com/example/android/networkusage/StackOverflowXmlParser.java?spec=svn.platform--development.99ca344b233f9419bf4cefc69997d54fc419b326&repo=platform--development&name=android-cts-4.2_r1&r=99ca344b233f9419bf4cefc69997d54fc419b326
// http://developer.android.com/training/basics/network-ops/xml.html	
	
	public static enum XmlTags {Data, Series, Episode};
	private static final String ns = null;

	public static HashMap<String, TvdbSerie> unmarshalSearchedSeries (String xml) throws TvdbAPIException{
		InputStream is = null;
		try {
			HashMap<String, TvdbSerie> series = new HashMap<String, TvdbSerie>();

			is = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(is, "UTF-8"); 
			parser.nextTag();
			parser.require(XmlPullParser.START_TAG, ns, XmlTags.Data.toString());

			while (parser.next() != XmlPullParser.END_TAG) {
				if (parser.getEventType() != XmlPullParser.START_TAG) {
					continue;
				}
				String name = parser.getName();
				// Starts by looking for the entry tag
				if (name.equals(XmlTags.Series.toString())) {
					TvdbSerie newSerie = readBasicSerieInfo(parser);
					series.put(newSerie.getId(), newSerie);
				} else if (name.equals(XmlTags.Episode.toString())){
					TvdbEpisode episode = readEpisodeInfo(parser);
					if (series.containsKey(episode.getSeriesid())){
						series.get(episode.getSeriesid()).addEpisode(episode);
					} else {
						Log.e(TvdbXML.class.getName(), "Unable to find the seriesID associated to the episode");
					}
					
				}else {
					skip(parser);
				}
			}
			return series;
		} catch (Exception e){
			throw new TvdbAPIException(e);
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					throw new TvdbAPIException(e);
				}
		}
	}
	
	private static TvdbEpisode readEpisodeInfo(XmlPullParser parser) throws XmlPullParserException, IOException{
		parser.require(XmlPullParser.START_TAG, ns, XmlTags.Episode.toString());
		
		String id = "", combined_episodenumber = "", combined_season = "",
		DVD_chapter = "", DVD_discid = "", DVD_episodenumber = "", DVD_season = "", director = "",
		epImgFlag = "", episodeName = "", episodeNumber = "", firstAired = "",
		guestStars = "", IMDB_ID = "", language = "", overview = "", productionCode = "",
		rating = "", ratingCount = "", seasonNumber = "", writer = "", absolute_number = "",
		airsafter_season = "", airsbefore_episode = "", airsbefore_season = "",
		filename = "", lastupdated = "", seasonid = "", seriesid = "", thumb_added = "",
		thumb_height = "", thumb_width = "", tms_export = "";

		while (parser.next() != XmlPullParser.END_TAG) {

			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			
			String name = parser.getName();
			if (name.equals(TvdbEpisode.XmlTags.id.toString())) {
				id = readText(parser, TvdbSerie.XmlTags.id.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.Combined_episodenumber.toString())) {
				combined_episodenumber = readText(parser, TvdbEpisode.XmlTags.Combined_episodenumber.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.Combined_season.toString())) {
				combined_season = readText(parser, TvdbEpisode.XmlTags.Combined_season.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.DVD_chapter.toString())) {
				DVD_chapter = readText(parser, TvdbEpisode.XmlTags.DVD_chapter.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.DVD_discid.toString())) {
				DVD_discid = readText(parser, TvdbEpisode.XmlTags.DVD_discid.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.DVD_episodenumber.toString())) {
				DVD_episodenumber = readText(parser, TvdbEpisode.XmlTags.DVD_episodenumber.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.DVD_season.toString())) {
				DVD_season = readText(parser, TvdbEpisode.XmlTags.DVD_season.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.Director.toString())) {
				director = readText(parser, TvdbEpisode.XmlTags.Director.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.EpImgFlag.toString())) {
				epImgFlag = readText(parser, TvdbEpisode.XmlTags.EpImgFlag.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.EpisodeName.toString())) {
				episodeName = readText(parser, TvdbEpisode.XmlTags.EpisodeName.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.EpisodeNumber.toString())) {
				episodeNumber = readText(parser, TvdbEpisode.XmlTags.EpisodeNumber.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.FirstAired.toString())) {
				firstAired = readText(parser, TvdbEpisode.XmlTags.FirstAired.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.GuestStars.toString())) {
				guestStars = readText(parser, TvdbEpisode.XmlTags.GuestStars.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.IMDB_ID.toString())) {
				IMDB_ID = readText(parser, TvdbEpisode.XmlTags.IMDB_ID.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.Language.toString())) {
				language = readText(parser, TvdbEpisode.XmlTags.Language.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.Overview.toString())) {
				overview = readText(parser, TvdbEpisode.XmlTags.Overview.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.ProductionCode.toString())) {
				productionCode = readText(parser, TvdbEpisode.XmlTags.ProductionCode.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.RatingCount.toString())) {
				ratingCount = readText(parser, TvdbEpisode.XmlTags.RatingCount.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.SeasonNumber.toString())) {
				seasonNumber = readText(parser, TvdbEpisode.XmlTags.SeasonNumber.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.Writer.toString())) {
				writer = readText(parser, TvdbEpisode.XmlTags.Writer.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.absolute_number.toString())) {
				absolute_number = readText(parser, TvdbEpisode.XmlTags.absolute_number.toString());
				
			} else if (name.equals(TvdbEpisode.XmlTags.airsafter_season.toString())) {
				airsafter_season = readText(parser, TvdbEpisode.XmlTags.airsafter_season.toString());	
				
			} else if (name.equals(TvdbEpisode.XmlTags.airsbefore_episode.toString())) {
				airsbefore_episode = readText(parser, TvdbEpisode.XmlTags.airsbefore_episode.toString());
				
			}else if (name.equals(TvdbEpisode.XmlTags.airsbefore_season.toString())) {
				airsbefore_season = readText(parser, TvdbEpisode.XmlTags.airsbefore_season.toString());	
				
			}else if (name.equals(TvdbEpisode.XmlTags.filename.toString())) {
				filename = readText(parser, TvdbEpisode.XmlTags.filename.toString());
				
			}else if (name.equals(TvdbEpisode.XmlTags.lastupdated.toString())) {
				lastupdated = readText(parser, TvdbEpisode.XmlTags.lastupdated.toString());	
				
			}else if (name.equals(TvdbEpisode.XmlTags.seasonid.toString())) {
				seasonid = readText(parser, TvdbEpisode.XmlTags.seasonid.toString());
				
			}else if (name.equals(TvdbEpisode.XmlTags.seriesid.toString())) {
				seriesid = readText(parser, TvdbEpisode.XmlTags.seriesid.toString());
				
			}else if (name.equals(TvdbEpisode.XmlTags.thumb_added.toString())) {
				thumb_added = readText(parser, TvdbEpisode.XmlTags.thumb_added.toString());	
				
			}else if (name.equals(TvdbEpisode.XmlTags.thumb_height.toString())) {
				thumb_height = readText(parser, TvdbEpisode.XmlTags.thumb_height.toString());
				
			}else if (name.equals(TvdbEpisode.XmlTags.thumb_width.toString())) {
				thumb_width = readText(parser, TvdbEpisode.XmlTags.thumb_width.toString());	
				
			}else if (name.equals(TvdbEpisode.XmlTags.tms_export.toString())) {
				tms_export = readText(parser, TvdbEpisode.XmlTags.tms_export.toString());	
				
			}else {
				skip(parser);
			}
		}
		
		TvdbEpisode episode = new TvdbEpisode(id)
			.setCombined_episodenumber(combined_episodenumber)
			.setCombined_season(combined_season)
			.setDVD_chapter(DVD_chapter)
			.setDVD_discid(DVD_discid)
			.setDVD_episodenumber(DVD_episodenumber)
			.setDVD_season(DVD_season)
			.setDirector(director)
			.setEpImgFlag(epImgFlag)
			.setEpisodeName(episodeName)
			.setEpisodeNumber(episodeNumber)
			.setFirstAired(firstAired)
			.setGuestStars(guestStars)
			.setIMDB_ID(IMDB_ID)
			.setLanguage(language)
			.setOverview(overview)
			.setProductionCode(productionCode)
			.setRating(rating)
			.setRatingCount(ratingCount)
			.setSeasonNumber(seasonNumber)
			.setWriter(writer)
			.setAbsolute_number(absolute_number)
			.setAirsafter_season(airsafter_season)
			.setAirsbefore_episode(airsbefore_episode)
			.setAirsbefore_season(airsbefore_season)
			.setFilename(filename)
			.setLastupdated(lastupdated)
			.setSeasonid(seasonid)
			.setSeriesid(seriesid)
			.setThumb_added(thumb_added)
			.setThumb_height(thumb_height)
			.setThumb_width(thumb_width)
			.setTms_export(tms_export);
		
		return episode;
	}

	private static TvdbSerie readBasicSerieInfo (XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, ns, XmlTags.Series.toString());
		
		String id = "", seriesid = "", language = "", seriesName = "",
				overview = "", firstAired = "", network = "", banner = "",
				actors = "", airs_DayOfWeek = "", airs_Time = "", ratingCount = "",
				genre = "", IMDB_ID = "", rating = "", runtime = "", status = "", 
				fanart = "", poster = "", zap2it_id = "", contentRating = "", 
				networkID = "", added = "", addedBy = "", lastupdated = "";
		

		while (parser.next() != XmlPullParser.END_TAG) {
			
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			
			String name = parser.getName();
			if (name.equals(TvdbSerie.XmlTags.id.toString())) {
				id = readText(parser, TvdbSerie.XmlTags.id.toString());
				
			} else if (name.equals(TvdbSerie.XmlTags.seriesID.toString())) {
				seriesid = readText(parser, TvdbSerie.XmlTags.seriesID.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.language.toString())) {
				language = readText(parser, TvdbSerie.XmlTags.language.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.SeriesName.toString())) {
				seriesName = readText(parser, TvdbSerie.XmlTags.SeriesName.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.Overview.toString())) {
				overview = readText(parser, TvdbSerie.XmlTags.Overview.toString());
				
			} else if (name.equals(TvdbSerie.XmlTags.FirstAired.toString())) {
				firstAired = readText(parser, TvdbSerie.XmlTags.FirstAired.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.Network.toString())) {
				network = readText(parser, TvdbSerie.XmlTags.Network.toString()); 
			
			} else if (name.equals(TvdbSerie.XmlTags.banner.toString())) {
				banner = readText(parser, TvdbSerie.XmlTags.banner.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.Actors.toString())) {
				actors = readText(parser, TvdbSerie.XmlTags.Actors.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.Airs_DayOfWeek.toString())) {
				airs_DayOfWeek = readText(parser, TvdbSerie.XmlTags.Airs_DayOfWeek.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.Airs_Time.toString())) {
				airs_Time = readText(parser, TvdbSerie.XmlTags.Airs_Time.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.RatingCount.toString())) {
				ratingCount = readText(parser, TvdbSerie.XmlTags.RatingCount.toString());
				
			} else if (name.equals(TvdbSerie.XmlTags.Genre.toString())) {
				genre = readText(parser, TvdbSerie.XmlTags.Genre.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.IMDB_ID.toString())) {
				IMDB_ID = readText(parser, TvdbSerie.XmlTags.IMDB_ID.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.Rating.toString())) {
				rating = readText(parser, TvdbSerie.XmlTags.Rating.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.Runtime.toString())) {
				runtime = readText(parser, TvdbSerie.XmlTags.Runtime.toString());
				
			} else if (name.equals(TvdbSerie.XmlTags.Status.toString())) {
				status = readText(parser, TvdbSerie.XmlTags.Status.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.fanart.toString())) {
				fanart = readText(parser, TvdbSerie.XmlTags.fanart.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.poster.toString())) {
				poster = readText(parser, TvdbSerie.XmlTags.poster.toString()); 
				
			} else if (name.equals(TvdbSerie.XmlTags.zap2it_id.toString())) {
				zap2it_id = readText(parser, TvdbSerie.XmlTags.zap2it_id.toString());
				
				
			} else if (name.equals(TvdbSerie.XmlTags.ContentRating.toString())) {
				contentRating = readText(parser, TvdbSerie.XmlTags.ContentRating.toString());
				
			}else if (name.equals(TvdbSerie.XmlTags.NetworkID.toString())) {
				networkID = readText(parser, TvdbSerie.XmlTags.NetworkID.toString());
				
			}else if (name.equals(TvdbSerie.XmlTags.added.toString())) {
				added = readText(parser, TvdbSerie.XmlTags.added.toString());
				
			}else if (name.equals(TvdbSerie.XmlTags.addedBy.toString())) {
				addedBy = readText(parser, TvdbSerie.XmlTags.addedBy.toString());
				
			}else if (name.equals(TvdbSerie.XmlTags.lastupdated.toString())) {
				lastupdated = readText(parser, TvdbSerie.XmlTags.lastupdated.toString());
				
			}else {
				skip(parser);
			}
		}
		
		TvdbSerie serie = new TvdbSerie(id)
			.setSeriesID(seriesid)
			.setLanguage(language)
			.setSeriesName(seriesName)
			.setOverview(overview)
			.setFirstAired(firstAired)
			.setNetwork(network)
			.setBanner(banner)
			.setActors(actors)
			.setAirs_DayOfWeek(airs_DayOfWeek)
			.setAirs_Time(airs_Time)
			.setRatingCount(ratingCount)
			.setGenre(genre)
			.setIMDB_ID(IMDB_ID)
			.setRating(rating)
			.setRuntime(runtime)
			.setStatus(status)
			.setFanart(fanart)
			.setPoster(poster)
			.setZap2it_id(zap2it_id)
			.setContentRating(contentRating)
			.setNetworkID(networkID)
			.setAdded(added)
			.setAddedBy(addedBy)
			.setLastupdated(lastupdated);
		return serie;
	} 

	private static String readText(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, tag);
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			parser.nextTag();
		}
		parser.require(XmlPullParser.END_TAG, ns, tag);
		return result;
	}

	// Skips tags the parser isn't interested in. Uses depth to handle nested tags. i.e.,
	// if the next tag after a START_TAG isn't a matching END_TAG, it keeps going until it
	// finds the matching END_TAG (as indicated by the value of "depth" being 0).
	private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
		if (parser.getEventType() != XmlPullParser.START_TAG) {
			throw new IllegalStateException();
		}
		int depth = 1;
		while (depth != 0) {
			switch (parser.next()) {
			case XmlPullParser.END_TAG:
				depth--;
				break;
			case XmlPullParser.START_TAG:
				depth++;
				break;
			}
		}
	}


}
