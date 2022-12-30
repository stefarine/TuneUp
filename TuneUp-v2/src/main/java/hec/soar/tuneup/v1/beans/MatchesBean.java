package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.models.Artist;
import hec.soar.tuneup.v1.models.Track;
import hec.soar.tuneup.v1.models.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "matchesBean")
@SessionScoped
public class MatchesBean implements Serializable {
    private  Map<User, ArrayList<User>> likeUser;
    
    public  void start(){
        MockDatabase db = MockDatabase.getInstance();
        this.likeUser = db.getLikeUser();
    }
    
    //////// BEAN STUFFF //////
    
    public ArrayList<User> getUserLoggedInMatches(){
        
        ArrayList<User> res = new ArrayList<>();
        
        MockDatabase db = MockDatabase.getInstance();
        Map<User, ArrayList<User>> userLikes = db.getLikeUser();
        ArrayList<User> userLoggedInLikes = userLikes.get(LoginBean.getUserLoggedIn());
        
        Iterator<User> userLoggedInLikesIterator = userLoggedInLikes.iterator();
        
        while (userLoggedInLikesIterator.hasNext()){
            User uP = userLoggedInLikesIterator.next();
            
            // il faut que l'utilisateur nous ait aussi liké pour que 
            // ça soit un match
            if(userLikes.get(uP).contains(LoginBean.getUserLoggedIn())){
                res.add(uP);
            }
        }
        
        return res;
 
    }
    
    ////// END BEAN STUFF /////
    
    
    
    public  ArrayList<User> getUserMatches(User u){

        
        ArrayList<User> res = new ArrayList<>();
        
        ArrayList<User> userUlike = this.likeUser.get(u);
        Iterator<User> userULikeIterator = userUlike.iterator();
        
        while (userULikeIterator.hasNext()){
            User uP = userULikeIterator.next();
            
            // il faut que l'utilisateur nous ait aussi liké pour que 
            // ça soit un match
            if (this.likeUser.get(uP).contains(u)){
                res.add(uP);
            }
        }
        
        
        return res;
    }
    
    public static ArrayList<User> getUserLikes(User u){
        MockDatabase db = MockDatabase.getInstance();
        Map<User, ArrayList<User>> likeUserr = db.getLikeUser();;
        
        return likeUserr.get(u);
    }
    
    public void addUserToMatchesList(User userToMatch) throws AlreadyExistsException{
        MockDatabase db = MockDatabase.getInstance();
        User u = LoginBean.getUserLoggedIn();
        ArrayList<User> ml = db.getLikeUser().get(u);
        
        if (!ml.contains(userToMatch)){
            ml.add(userToMatch);
        }
        else{
            throw new AlreadyExistsException("The User "+userToMatch.getFirst_name()+" is already in your matches.");  
        }
    }
    
    public void removeUserFromMatchesList(User userToRemove) throws AlreadyExistsException{
        MockDatabase db = MockDatabase.getInstance();
        User u = LoginBean.getUserLoggedIn();
        ArrayList<User> ml = db.getLikeUser().get(u);
        
        if (ml.contains(userToRemove)){
            ml.remove(userToRemove);
        }
        else{
            throw new AlreadyExistsException("The User "+userToRemove.getFirst_name()+" is NOT in your matches.");  
        }
    }
    
    
    public ArrayList<User> getNotMatchedUsers(){
        MockDatabase db = MockDatabase.getInstance();
        User u = LoginBean.getUserLoggedIn();
        
        ArrayList<User> allUsers = new ArrayList(db.getUsers().values());
        ArrayList<User> matchedUsers = MatchesBean.getUserLikes(u);
        
        ArrayList<User> notMatchedUsers = new ArrayList(allUsers);
        
        if (matchedUsers != null) {
            notMatchedUsers.removeAll(matchedUsers);
        }
        return notMatchedUsers;
    }
    
    // "Algo" de proposition des user en fonction des gouts musicaux
    public  ArrayList<User> getPotentialMatchUsers(){
        MockDatabase db = MockDatabase.getInstance();
        User u = LoginBean.getUserLoggedIn();
        
        
        ArrayList<User> potentialMatches = new ArrayList();
        
        
        ArrayList<User> notMatchedUsers = getNotMatchedUsers();
        
        if (notMatchedUsers != null) {
            Iterator<User> usI = notMatchedUsers.iterator();

            while(usI.hasNext()){
                User uPotential = usI.next();

                ArrayList<String> myGenres = getUserGenres(u);
                ArrayList<String> otherUserGenres = getUserGenres(uPotential);

                myGenres.retainAll(otherUserGenres);

                if (!myGenres.isEmpty()){
                    if (!u.equals(uPotential))
                    potentialMatches.add(uPotential);
                }

            }
        }
        
        
        System.out.println(potentialMatches);
        return potentialMatches;
    }
    
    
    public ArrayList<String> getUserGenres(User u){
        MockDatabase db = MockDatabase.getInstance();
    
        Map<Artist, ArrayList<Track>> trackByArtist = db.getTrackByArtist();
        ArrayList<Track> myPl = PlaylistBean.getUserPlaylist(u);
        ArrayList<Artist> myArtists = new ArrayList();
        ArrayList<String> myGenres = new ArrayList();
        
        Iterator<Artist> artistIterator = trackByArtist.keySet().iterator();
        while(artistIterator.hasNext()){
            Artist a = artistIterator.next();
            
            if (myPl != null){
                Iterator<Track> myPlIterator = myPl.iterator();
                while (myPlIterator.hasNext()){
                    Track t = myPlIterator.next();

                    if (trackByArtist.get(a).contains(t)){
                        myArtists.add(a);
                    }
                }
            }
        }
        
        if (myArtists != null){
            Iterator<Artist> myArtistsIterator = myArtists.iterator();
            while(myArtistsIterator.hasNext()){
                Artist a = myArtistsIterator.next();
                myGenres.addAll(Arrays.asList(a.getGenres()));
            }
        }
    
        return myGenres;
    }
    
    /*
    public static boolean containsUser(User u){
        ArrayList<User> ml = getUserLikes(LoginBean.getUserLoggedIn());
        
        return ml.contains(u);
    }
    */
    
}
