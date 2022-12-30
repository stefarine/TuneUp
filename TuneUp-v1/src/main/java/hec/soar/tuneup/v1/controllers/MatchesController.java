package hec.soar.tuneup.v1.controllers;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.models.Artist;
import hec.soar.tuneup.v1.models.Track;
import hec.soar.tuneup.v1.models.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class MatchesController {
    private static Map<User, ArrayList<User>> likeUser;
    
    public static void start(){
        MockDatabase db = MockDatabase.getInstance();
        likeUser = db.getLikeUser();
    }
    
    public static ArrayList<User> getUserMatches(User u){

        
        ArrayList<User> res = new ArrayList<>();
        
        ArrayList<User> userUlike = likeUser.get(u);
        Iterator<User> userULikeIterator = userUlike.iterator();
        
        while (userULikeIterator.hasNext()){
            User uP = userULikeIterator.next();
            
            // il faut que l'utilisateur nous ait aussi liké pour que 
            // ça soit un match
            if (likeUser.get(uP).contains(u)){
                res.add(uP);
            }
        }
        
        
        return res;
    }
    
    public static ArrayList<User> getUserLikes(User u){
        return likeUser.get(u);
    }
    
    public static void addUserToMatchesList(User userToMatch) throws AlreadyExistsException{
        MockDatabase db = MockDatabase.getInstance();
        User u = LoginController.getUserLoggedIn();
        ArrayList<User> ml = db.getLikeUser().get(u);
        
        if (!ml.contains(userToMatch)){
            ml.add(userToMatch);
        }
        else{
            throw new AlreadyExistsException("The User "+userToMatch.getFirst_name()+" is already in your matches.");  
        }
    }
    
    public static void removeUserFromMatchesList(User userToRemove) throws AlreadyExistsException{
        MockDatabase db = MockDatabase.getInstance();
        User u = LoginController.getUserLoggedIn();
        ArrayList<User> ml = db.getLikeUser().get(u);
        
        if (ml.contains(userToRemove)){
            ml.remove(userToRemove);
        }
        else{
            throw new AlreadyExistsException("The User "+userToRemove.getFirst_name()+" is NOT in your matches.");  
        }
    }
    
    public static ArrayList<User> getNotMatchedUsers(){
        MockDatabase db = MockDatabase.getInstance();
        User u = LoginController.getUserLoggedIn();
        
        ArrayList<User> allUsers = new ArrayList(db.getUsers().values());
        ArrayList<User> matchedUsers = MatchesController.getUserLikes(u);
        
        ArrayList<User> notMatchedUsers = new ArrayList(allUsers);
        
        if (matchedUsers != null) {
            notMatchedUsers.removeAll(matchedUsers);
        }
        return notMatchedUsers;
    }
    
    // "Algo" de proposition des user en fonction des gouts musicaux
    public static ArrayList<User> getPotentialMatchUsers(){
        MockDatabase db = MockDatabase.getInstance();
        User u = LoginController.getUserLoggedIn();
        
        
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
        
        
        
        return potentialMatches;
    }
    
    public static ArrayList<String> getUserGenres(User u){
        MockDatabase db = MockDatabase.getInstance();
    
        Map<Artist, ArrayList<Track>> trackByArtist = db.getTrackByArtist();
        ArrayList<Track> myPl = PlaylistController.getUserPlaylist(u);
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
    
    public static boolean containsUser(User u){
        ArrayList<User> ml = getUserLikes(LoginController.getUserLoggedIn());
        
        return ml.contains(u);
    }
    
}
