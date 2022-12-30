package hec.soar.tuneup.v1.beans;
import hec.soar.tuneup.v1.models.Users;
import hec.soar.tuneup.v4.client.PersistenceClient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value = "matchesBean")
@SessionScoped
public class MatchesBean implements Serializable {

       
    public List<Users> getUserLoggedInMatches(){

        List<Users> allUsers = PersistenceClient.getInstance().getAllUsers();
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        
        List<Users> matches = new ArrayList<>();
        
        for (int i = 0 ; i< allUsers.size(); i++){
            List<Users> uL = allUsers.get(i).getUsersCollection();
            
            for (int j = 0; j<uL.size(); j++){
                if(uL.get(j).getEmail().equals(uLoggedIn.getEmail())){
                    matches.add(allUsers.get(i));
                }
                
                
            }
        
        }
        
        
        return matches;
    }
    
    public List<Users> getPotentialMatchUsers(){

        List<Users> allUsers = PersistenceClient.getInstance().getAllUsers();
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        
        List<Users> matches = getUserLoggedInMatches();
        
        List<Users> res = new ArrayList<>();
        
        for (int i = 0 ; i < allUsers.size(); i++){
            
            for (int j = 0; j < matches.size();j++){
                String email_all = allUsers.get(i).getEmail();
                String email_match = matches.get(j).getEmail();
                
                if (email_all.equals(email_match)){
                    allUsers.remove(allUsers.get(i));
                }
                
            
            }
        
        }
        
        
        
        return allUsers;
    }
    
    
    public void removeUserFromMatchesList(Users u){
        List<Users> allTracks = PersistenceClient.getInstance().getAllUsers();
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        System.out.println("I remove> "+u.getEmail());
        PersistenceClient.getInstance().removeUserFromMatchesList(u);
    }
    
      public void addUserToMatchesList(Users u){
        List<Users> allTracks = PersistenceClient.getInstance().getAllUsers();
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        System.out.println("I add> "+u.getEmail());
        PersistenceClient.getInstance().addUserFromMatchesList(u);
    }
    
    
    /*@Transactional
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
    }*/
    
  
}
