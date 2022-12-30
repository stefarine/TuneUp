
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


@Named(value = "artistBean")
@SessionScoped
public class artistBean implements Serializable{
    private static Map<String, Artist> artists;
    private static Artist artist;
    private static ArrayList<Track> tracks;
    
    public static void start(){
        MockDatabase db = MockDatabase.getInstance();
        artists = db.getArtists();
    }
    
    ///// BEAN STUFF ///////
    
    public ArrayList<Track> getTracksByArtist(String artistName){
        MockDatabase db = MockDatabase.getInstance();
        Map<String, Artist> artistsList = db.getArtists();
        
        System.out.println(artistName);
        Set<String> keys = artistsList.keySet();
        Iterator<String> keysIterator = keys.iterator();
        
        
        while(keysIterator.hasNext()){
            String k = keysIterator.next();
            if (artistsList.get(k).getName().toLowerCase().equals(artistName.toLowerCase())){
                //get all tracks from this artist
                Artist a = artistsList.get(k);
                ArrayList<Track> artistTracklist = db.getTrackByArtist().get(a);
                artistTracklist.forEach(e-> System.out.print(e.getName()));
                
                // get user logged in playlist
                ArrayList<Track> userLogedInPlaylist = getUserLoggedInPlaylist();
                System.out.println(">>+++"+artistTracklist);
                
                // take difference between all artist's track and user logged in playlist
                // https://www.baeldung.com/java-lists-difference
                
                // j'ai besoin d'une copie sinon artistTracklist sera pas comme je veux si je reviens
                ArrayList<Track> res = (ArrayList) artistTracklist.clone();
                
                
                res.removeAll(userLogedInPlaylist);
                return res; 
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
    
    public static Map<String, Artist> getAllArtists(){
        start();
        return artists; 
    }
    
    public static void setArtist(Artist a) throws DoesNotExistException{
        MockDatabase db = MockDatabase.getInstance();
        
        if (db.getArtists().containsKey(a.getId())){
            artistBean.artist = a;
            artistBean.tracks = db.getTrackByArtist().get(a);
        }
        else {
            throw new DoesNotExistException("The Artist "+a.getName()+" does not exist");
        }
        
    }
    
    public static Artist getArtist(){
        return artist;
    }
    
    public static ArrayList<Track> getArtistTracks(){
        return artistBean.tracks;
    }
    
    
    
    
    public static boolean getArtistByName(String artistName) throws DoesNotExistException {
        
        Set<String> keys = artists.keySet();
        Iterator<String> keysIterator = keys.iterator();
        
        boolean exist = false;
        
        while(keysIterator.hasNext()) {
            String k = keysIterator.next();
            if (artists.get(k).getName().toLowerCase().equals(artistName.toLowerCase())){
                artistBean.setArtist(artists.get(k));
                exist = true;
            }
            
        }
        if (exist){
            return true;
        }
        throw new DoesNotExistException("The artist \""+artistName+"\" does not exist.");
    } 
}
