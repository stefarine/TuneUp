package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.models.Track;
import hec.soar.tuneup.v1.models.Users;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Named(value = "playlistBean")
@SessionScoped
public class PlaylistBean implements Serializable {
    
    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
     
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

    @Transactional
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
    public void removeTrackFromPlaylistOfUserLoggedIn(Track t){
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        
        if (isTrackInThePlaylist(uLoggedIn,t.getIdTrack())){
            Query query3 = em.createNamedQuery("Track.findByIdTrack",Track.class);
            List<Track> tracks = query3.setParameter("idTrack", t.getIdTrack()).getResultList();
            Track track = tracks.get(0);
            Collection<Users> trackIsInThePlaylistOf = track.getUsersCollection();
            
            trackIsInThePlaylistOf.remove(uLoggedIn);
            
            em.merge(track);
        }
        
    }
    
    public boolean isTrackInThePlaylist(Users u, String trackID){
        Query query3 = em.createNamedQuery("Track.findByIdTrack",Track.class);
        List<Track> tracks = query3.setParameter("idTrack", trackID).getResultList();
        Track track = tracks.get(0);
        
        return track.getUsersCollection().contains(u);
    }
    

    
    
}
