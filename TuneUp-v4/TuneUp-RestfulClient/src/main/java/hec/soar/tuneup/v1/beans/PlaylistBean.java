package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.models.Track;
import hec.soar.tuneup.v1.models.Users;
import hec.soar.tuneup.v4.client.PersistenceClient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value = "playlistBean")
@SessionScoped
public class PlaylistBean implements Serializable {
    
    public List<Track> getUserPlaylist(Users u){
        //getAllTracks
        List<Track> allTracks = PersistenceClient.getInstance().getAllTracks();
        
        
        
        //Iterate in allTracks and find in which uLoggedIn Appears in collection
        List<Track> userPlaylist = new ArrayList<>();
        
        Iterator<Track> iT = allTracks.iterator();
        while(iT.hasNext()){
            Track t = iT.next();
            
            //System.out.println("--------\n\nTRACK : "+t.getName());
            //t.getUsersCollection().forEach(us-> System.out.println(us.getEmail()));
            
            Iterator<Users> uI = t.getUsersCollection().iterator();
            while(uI.hasNext()){
            Users uu = uI.next();
                //System.out.println(uu.getEmail().equals(u.getEmail()));
                if (!userPlaylist.contains(t)){
                    if (uu.getEmail().equals(u.getEmail())){
                        userPlaylist.add(t);
                    }
                }    
            }
            
            //System.out.println("---------\n\n");

            
        }
        
        
        return userPlaylist;
        
    }
    
    public List<Track> getUserLoggedInPlaylist(){
        
        //get UserLoggedIn
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        return getUserPlaylist(uLoggedIn);
    }
    
    /*public void removeTrackFromPlaylistOfUserLoggedIn(Track t){
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        
        if (isTrackInThePlaylist(uLoggedIn,t.getIdTrack())){
            Query query3 = em.createNamedQuery("Track.findByIdTrack",Track.class);
            List<Track> tracks = query3.setParameter("idTrack", t.getIdTrack()).getResultList();
            Track track = tracks.get(0);
            Collection<Users> trackIsInThePlaylistOf = track.getUsersCollection();
            
            trackIsInThePlaylistOf.remove(uLoggedIn);
            
            em.merge(track);
        }
        
    }*/
    
    public void removeTrackFromPlaylistOfUserLoggedIn(Track t){
        List<Track> allTracks = PersistenceClient.getInstance().getAllTracks();
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        System.out.println("I remove> "+t.getName());
        PersistenceClient.getInstance().removeTrackFromPlaylist(t, uLoggedIn);
    }
    
    public void addTrackToPlaylist(Track t){
        List<Track> allTracks = PersistenceClient.getInstance().getAllTracks();
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        System.out.println("I remove> "+t.getName());
        PersistenceClient.getInstance().addTrackFromPlaylist(t, uLoggedIn);
    }

    /*@Transactional
    public void addTrackToPlaylist(Track t){
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        
        Query query3 = em.createNamedQuery("Track.findByIdTrack",Track.class);
        List<Track> tracks = query3.setParameter("idTrack", t.getIdTrack()).getResultList();
        Track track = tracks.get(0);
        Collection<Users> trackIsInThePlaylistOf = track.getUsersCollection();
        
        trackIsInThePlaylistOf.add(uLoggedIn);
        
        em.merge(track);
    }
    
    @Transactional
    
    
    public boolean isTrackInThePlaylist(Users u, String trackID){
        Query query3 = em.createNamedQuery("Track.findByIdTrack",Track.class);
        List<Track> tracks = query3.setParameter("idTrack", trackID).getResultList();
        Track track = tracks.get(0);
        
        return track.getUsersCollection().contains(u);
    }
    */

    
    
}
