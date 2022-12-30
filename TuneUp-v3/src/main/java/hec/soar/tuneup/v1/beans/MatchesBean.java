package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.models.Artist;
import hec.soar.tuneup.v1.models.Track;
import hec.soar.tuneup.v1.models.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Named(value = "matchesBean")
@SessionScoped
public class MatchesBean implements Serializable {
    private  Map<Users, ArrayList<Users>> likeUser;
    
    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
    
    
    public ArrayList<Users> getUserLoggedInMatches(){
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        Query query = em.createNamedQuery("Users.findAll",Users.class);
        List<Users> usrs = query.getResultList();
        
        ArrayList<Users> uLoggedInMatches = new ArrayList<>();
        Iterator<Users> uI = usrs.iterator();
        
        while(uI.hasNext()){
            Users u = uI.next();
            if (u.getUsersCollection().contains(uLoggedIn) && u.getUsersCollection1().contains(uLoggedIn)){
                uLoggedInMatches.add(u);
            }
            
        }

        return uLoggedInMatches;
    }
    
    @Transactional
    public void removeUserFromMatchesList(Users userToRemove){
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        
        Query query = em.createNamedQuery("Users.findByEmail",Users.class);
        List<Users> usrsList = query.setParameter("email", userToRemove.getEmail()).getResultList();
        Users uToRmv = usrsList.get(0);
        
        uToRmv.getUsersCollection().remove(uLoggedIn);

        em.merge(uLoggedIn);
    }
    
    public  ArrayList<Users> getPotentialMatchUsers(){
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        Query query = em.createNamedQuery("Users.findAll",Users.class);
        List<Users> usrs = query.getResultList();
        ArrayList<Users> potentialMatches = new ArrayList();
        
        // NOT MATCHED USERS
        Query query2 = em.createNamedQuery("Users.findAll",Users.class);
        List<Users> allUsers = query2.getResultList();
        
        ArrayList<Users> uLoggedInMatches = getUserLoggedInMatches();
        
        ArrayList<Users> uLoggedInLikes = new ArrayList<>();
        
        Iterator<Users> uI = allUsers.iterator();
        
        while(uI.hasNext()){
            Users u = uI.next();
            if (u.getUsersCollection().contains(uLoggedIn)){
                uLoggedInLikes.add(u);
            }
            if (!uLoggedInMatches.contains(u) && !uLoggedInLikes.contains(u)){
                if (!u.getEmail().equals(uLoggedIn.getEmail())){
                    if (haveSameMuscialTastes(u)){
                        potentialMatches.add(u);
                    }
                }
            }
        }
        
        potentialMatches.forEach(e->System.out.println("popo "+e.getEmail()));
        return potentialMatches;
    }
    
    public boolean haveSameMuscialTastes(Users uToCompare){
    
        ArrayList<Track> userLoggedInPlaylist = getUserLoggedInPlaylist();
        ArrayList<Track> uToComparePlaylist =  getUserPlaylist(uToCompare);
        
        userLoggedInPlaylist.retainAll(uToComparePlaylist);
        
        return !userLoggedInPlaylist.isEmpty();
    }
    
    @Transactional
    public String addUserToMatchesList(Users userTolike) {
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        
        Query query = em.createNamedQuery("Users.findByEmail",Users.class);
        List<Users> usrsList = query.setParameter("email", userTolike.getEmail()).getResultList();
        Users usrToLike = usrsList.get(0);
        
        usrToLike.getUsersCollection().add(uLoggedIn);

        em.merge(uLoggedIn);
        
        return "/faces/views/MainMenu/userHomePage.xhtml?faces-redirect=true";
        
    }
    
    public ArrayList<Track> getUserPlaylist(Users u){
        //getAllTracks
        Query query3 = em.createNamedQuery("Track.findAll",Track.class);
        List<Track> allTracks = query3.getResultList();
        
        //Iterate in allTracks and find in which uLoggedIn Appears in collection
        ArrayList<Track> userPlaylist = new ArrayList<>();
        Iterator<Track> allTracksIterator = allTracks.iterator();
        while(allTracksIterator.hasNext()){
            Track tr = allTracksIterator.next();
            
            if(tr.getUsersCollection().contains(u)){
                userPlaylist.add(tr);
            }
        }

        return userPlaylist;
        
    }
    
    public ArrayList<Track> getUserLoggedInPlaylist(){
        
        //get UserLoggedIn
        Users uLoggedIn = LoginBean.getUserLoggedIn();

        return getUserPlaylist(uLoggedIn);
    }
    
  
}
