
package hec.soar.tuneup.v1.controllers;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import hec.soar.tuneup.v1.models.Artist;
import hec.soar.tuneup.v1.models.Track;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ArtistController {
    private static Map<String, Artist> artists;
    private static Artist artist;
    private static ArrayList<Track> tracks;
    
    public static void start(){
        MockDatabase db = MockDatabase.getInstance();
        artists = db.getArtists();
    }
    
    public static Map<String, Artist> getAllArtists(){
        start();
        return artists; 
    }
    
    public static void setArtist(Artist a) throws DoesNotExistException{
        MockDatabase db = MockDatabase.getInstance();
        
        if (db.getArtists().containsKey(a.getId())){
            ArtistController.artist = a;
            ArtistController.tracks = db.getTrackByArtist().get(a);
        }
        else {
            throw new DoesNotExistException("The Artist "+a.getName()+" does not exist");
        }
        
    }
    
    public static Artist getArtist(){
        return artist;
    }
    
    public static ArrayList<Track> getArtistTracks(){
        return ArtistController.tracks;
    }
    
    
    
    
    public static boolean getArtistByName(String artistName) throws DoesNotExistException {
        
        Set<String> keys = artists.keySet();
        Iterator<String> keysIterator = keys.iterator();
        
        boolean exist = false;
        
        while(keysIterator.hasNext()) {
            String k = keysIterator.next();
            if (artists.get(k).getName().toLowerCase().equals(artistName.toLowerCase())){
                ArtistController.setArtist(artists.get(k));
                exist = true;
            }
            
        }
        if (exist){
            return true;
        }
        throw new DoesNotExistException("The artist \""+artistName+"\" does not exist.");
    } 
}
