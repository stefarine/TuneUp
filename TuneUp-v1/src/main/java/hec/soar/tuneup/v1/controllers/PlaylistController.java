package hec.soar.tuneup.v1.controllers;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import hec.soar.tuneup.v1.models.Track;
import hec.soar.tuneup.v1.models.User;
import java.util.ArrayList;
import java.util.Map;

public class PlaylistController {
    private static Map<User, ArrayList<Track>> AllPlaylists;
    
    public static void start(){
        MockDatabase db = MockDatabase.getInstance();
        AllPlaylists = db.getInThePlaylist();
    }
    
    public static ArrayList<Track> getUserPlaylist(User u){
        return AllPlaylists.get(u);
    }
    
    public static void addTrackToPlaylist(Track t) throws AlreadyExistsException{
        MockDatabase db = MockDatabase.getInstance();
        User u = LoginController.getUserLoggedIn();
        ArrayList<Track> pl = db.getInThePlaylist().get(u);
        
        if (!pl.contains(t)){
            pl.add(t);
        }
        else{
            throw new AlreadyExistsException("The Track "+t.getName()+" is already in your playlist");  
        }
    }
    
    public static void removeTrackFromPlaylist(Track t) throws DoesNotExistException{
        MockDatabase db = MockDatabase.getInstance();
        User u = LoginController.getUserLoggedIn();
        ArrayList<Track> pl = db.getInThePlaylist().get(u);
        
        if (pl.contains(t)){
            pl.remove(t);
        }
        else{
            throw new DoesNotExistException("The Track "+t.getName()+" is NOT in your playlist");  
        }
    }
    
    public static boolean containsTrack(Track t){
        ArrayList<Track> pl = getUserPlaylist(LoginController.getUserLoggedIn());
        
        if (pl != null){
            return pl.contains(t);
        }
        else{
            return false;
        }
    }
}
