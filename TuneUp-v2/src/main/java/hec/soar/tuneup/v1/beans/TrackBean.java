package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import hec.soar.tuneup.v1.models.Artist;
import hec.soar.tuneup.v1.models.Track;
import hec.soar.tuneup.v1.models.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "trackBean")
@SessionScoped
public class TrackBean implements Serializable{
    private static Map<String, Track> tracks;
    private Map<Artist, ArrayList<Track>> trackByArtist;
    
    private Track track;
    private Artist artist;
    
    
    public  void start(){
        MockDatabase db = MockDatabase.getInstance();
        tracks = db.getTracks();
        trackByArtist = db.getTrackByArtist();
    }
    
    ///// BEAN STUFF ///////
    
    public Artist getArtistByTrack(Track t){
        MockDatabase db = MockDatabase.getInstance();
        Artist a = null;
        
        Map<Artist, ArrayList<Track>> tba = db.getTrackByArtist();
        
        Set<Artist> artistKeys = tba.keySet();
        Iterator<Artist> artistIterator = artistKeys.iterator();

        while (artistIterator.hasNext()){
            Artist arti = artistIterator.next();
            if (tba.get(arti).contains(t)){
                a = arti;
            }
        }
        
        
        return a;
    }
    
    public Track getTracksByName(String trackName){
        MockDatabase db = MockDatabase.getInstance();
        Map<String, Track> trackList = db.getTracks();
        
        System.out.println(trackName);
        Set<String> keys = trackList.keySet();
        Iterator<String> keysIterator = keys.iterator();
        
        
        while(keysIterator.hasNext()){
            String k = keysIterator.next();
            if (trackList.get(k).getName().toLowerCase().equals(trackName.toLowerCase())){
                //get the track with this name
                Track t = trackList.get(k);
                ArrayList<Track> userLogedInPlaylist = getUserLoggedInPlaylist();
                
                if (!userLogedInPlaylist.contains(t)){
                    return t;
                }
            }
        }
        
        return null;

    }
    
    public ArrayList<Track> getUserLoggedInPlaylist(){
        MockDatabase db = MockDatabase.getInstance();
        Map<User, ArrayList<Track>> allPlaylists = db.getInThePlaylist();        
        ArrayList<Track> UserLoggedInPlaylist = allPlaylists.get(LoginBean.getUserLoggedIn());
        System.out.println(UserLoggedInPlaylist);
        return UserLoggedInPlaylist;
    }
    
    
    ///// END BEAN STUFF /////
    
    public static Map<String, Track> getAllTracks(){
        return tracks; 
    }
    
    public  void setTrack(Track t) {
        this.track = t;
        
        Set<Artist> artistKeys = trackByArtist.keySet();
        Iterator<Artist> artistIterator = artistKeys.iterator();

        while (artistIterator.hasNext()){
            Artist arti = artistIterator.next();
            if (trackByArtist.get(arti).contains(t)){
                this.setArtist(arti);
            }
        }
    }
    
    public Artist getArtistForTrack(Track t){
        this.track = t;
        Artist a = null;
        
        Set<Artist> artistKeys = trackByArtist.keySet();
        Iterator<Artist> artistIterator = artistKeys.iterator();

        while (artistIterator.hasNext()){
            Artist arti = artistIterator.next();
            if (trackByArtist.get(arti).contains(t)){
                a = arti;
            }
        }
        return a;
    }
    
    public  Track getTrack(){
        return track;
    }
    
    public  void setArtist(Artist a) {
        this.artist = a;
    }
    
    public Artist getArtist(){
        return artist;
    }
    
    
    
    public boolean getTrackByName(String trackName) throws DoesNotExistException {
        
        Set<String> trackKeys = tracks.keySet();
        Iterator<String> keysIterator = trackKeys.iterator();
        
        Set<Artist> artistKeys = trackByArtist.keySet();
        Iterator<Artist> artistIterator = artistKeys.iterator();
        
        
        boolean exist = false;
        
        while(keysIterator.hasNext()) {
            String k = keysIterator.next();
            if (tracks.get(k).getName().toLowerCase().equals(trackName.toLowerCase())){
                this.setTrack(tracks.get(k));
                
                while(artistIterator.hasNext()){
                    Artist a = artistIterator.next();
                    if (trackByArtist.get(a).contains(tracks.get(k))){
                        this.setArtist(a);
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