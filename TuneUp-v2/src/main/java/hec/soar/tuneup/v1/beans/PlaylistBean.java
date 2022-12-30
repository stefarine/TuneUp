package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import hec.soar.tuneup.v1.models.Track;
import hec.soar.tuneup.v1.models.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "playlistBean")
@SessionScoped
public class PlaylistBean implements Serializable {
    private  Map<User, ArrayList<Track>> AllPlaylists;
    private ArrayList<Track> userLoggedInPlaylist;
    
    private String searchByChoice; 
    
    
    public void start(){
        MockDatabase db = MockDatabase.getInstance();
        AllPlaylists = db.getInThePlaylist();
        
        Map<String, User> users = db.getUsers();
        User u = users.get(LoginBean.getUserLoggedIn().getEmail());
        
        userLoggedInPlaylist = AllPlaylists.get(LoginBean.getUserLoggedIn());
        
    }
    
    public  static ArrayList<Track> getUserPlaylist(User u){
        Map<User, ArrayList<Track>> AllPlaylistss;
        MockDatabase db = MockDatabase.getInstance();
        AllPlaylistss = db.getInThePlaylist();
        
        return AllPlaylistss.get(u);
    }
    

    
    ////// STUFF FOR BEAN //////
    
        /*public  ArrayList<Track> getUserPlaylistByName(String email){
        MockDatabase db = MockDatabase.getInstance();
        Map<String, User> users = db.getUsers();
        User u = users.get(email);
        
        return AllPlaylists.get(u);
         }*/
    
    public ArrayList<Track> getUserLoggedInPlaylist(){
        MockDatabase db = MockDatabase.getInstance();
        Map<User, ArrayList<Track>> allPlaylists = db.getInThePlaylist();        
        ArrayList<Track> UserLoggedInPlaylist = allPlaylists.get(LoginBean.getUserLoggedIn());
        System.out.println(UserLoggedInPlaylist);
        return UserLoggedInPlaylist;
    }
    
    public void setOption(String option) {
        this.searchByChoice = option;
    }
    
    public String getOption() {
        return searchByChoice;
    }
    
    
    //// END STUFF FOR BEAN /////
    

    
    public void addTrackToPlaylist(Track t) throws AlreadyExistsException{
        System.out.println(t.getName()+" added");
        MockDatabase db = MockDatabase.getInstance();
        User u;
        u = LoginBean.getUserLoggedIn();
        ArrayList<Track> pl = db.getInThePlaylist().get(u);
        
        if (!pl.contains(t)){
            pl.add(t);
        }
        else{
            throw new AlreadyExistsException("The Track "+t.getName()+" is already in your playlist");  
        }
    }
    
    public void removeTrackFromPlaylist(Track t) throws DoesNotExistException{
        System.out.println(t.getName()+" removed");
        MockDatabase db = MockDatabase.getInstance();
        User u = LoginBean.getUserLoggedIn();
        ArrayList<Track> pl = db.getInThePlaylist().get(u);
        
        if (pl.contains(t)){
            pl.remove(t);
        }
        else{
            throw new DoesNotExistException("The Track "+t.getName()+" is NOT in your playlist");  
        }
    }
    
    public boolean containsTrack(Track t){
        ArrayList<Track> pl = getUserPlaylist(LoginBean.getUserLoggedIn());
        
        if (pl != null){
            return pl.contains(t);
        }
        else{
            return false;
        }
    }
}
