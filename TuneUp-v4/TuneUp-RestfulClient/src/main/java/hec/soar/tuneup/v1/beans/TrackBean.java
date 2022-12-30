package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.models.Artist;
import hec.soar.tuneup.v1.models.Track;
import hec.soar.tuneup.v1.models.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named(value = "trackBean")
@SessionScoped
public class TrackBean implements Serializable{

    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
    
    public Artist getArtistByTrack(Track t){
           
        ArrayList<Artist> aL = new ArrayList<>(t.getArtistCollection());
        
        return aL.get(0);
    }
    
    public Track getTracksByName(String trackName){

        Query query = em.createNamedQuery("Track.findByName",Track.class);
        List<Track> tracks = query.setParameter("name", trackName).getResultList();
        Track t = tracks.get(0);
        
        if (!t.getUsersCollection().contains(LoginBean.getUserLoggedIn())){
            return tracks.get(0);
        }
        
        else {
            return null;
        }
        
    }
    
}