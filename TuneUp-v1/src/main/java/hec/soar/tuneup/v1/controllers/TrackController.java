package hec.soar.tuneup.v1.controllers;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import hec.soar.tuneup.v1.models.Artist;
import hec.soar.tuneup.v1.models.Track;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class TrackController {
    private static Map<String, Track> tracks;
    private static Map<Artist, ArrayList<Track>> trackByArtist;
    
    private static Track track;
    private static Artist artist;
    
    
    public static void start(){
        MockDatabase db = MockDatabase.getInstance();
        tracks = db.getTracks();
        trackByArtist = db.getTrackByArtist();
    }
    
    public static Map<String, Track> getAllTracks(){
        return tracks; 
    }
    
    public static void setTrack(Track t) {
        TrackController.track = t;
        
        Set<Artist> artistKeys = trackByArtist.keySet();
        Iterator<Artist> artistIterator = artistKeys.iterator();

        while (artistIterator.hasNext()){
            Artist arti = artistIterator.next();
            if (trackByArtist.get(arti).contains(t)){
                TrackController.setArtist(arti);
            }
        }
    }
    
    public static Track getTrack(){
        return track;
    }
    
    public static void setArtist(Artist a) {
        TrackController.artist = a;
    }
    
    public static Artist getArtist(){
        return artist;
    }
    
    
    
    public static boolean getTrackByName(String trackName) throws DoesNotExistException {
        
        Set<String> trackKeys = tracks.keySet();
        Iterator<String> keysIterator = trackKeys.iterator();
        
        Set<Artist> artistKeys = trackByArtist.keySet();
        Iterator<Artist> artistIterator = artistKeys.iterator();
        
        
        boolean exist = false;
        
        while(keysIterator.hasNext()) {
            String k = keysIterator.next();
            if (tracks.get(k).getName().toLowerCase().equals(trackName.toLowerCase())){
                TrackController.setTrack(tracks.get(k));
                
                while(artistIterator.hasNext()){
                    Artist a = artistIterator.next();
                    if (trackByArtist.get(a).contains(tracks.get(k))){
                        TrackController.setArtist(a);
                    }                
                }
                
                
                
                exist = true;
            }
            
        }
        if (exist){
            return true;
        }
        throw new DoesNotExistException("The track \""+trackName+"\" does not exist.");
    } 
}