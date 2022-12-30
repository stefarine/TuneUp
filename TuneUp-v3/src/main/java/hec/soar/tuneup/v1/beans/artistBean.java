package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import hec.soar.tuneup.v1.models.Artist;
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


@Named(value = "artistBean")
@SessionScoped
public class artistBean implements Serializable{

    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
    
    
    public Collection<Track> getTracksByArtist(String artistName){
        
        Query query = em.createNamedQuery("Artist.findByName", Artist.class);
        List<Artist> aList = query.setParameter("name", artistName).getResultList();
        Artist a = aList.get(0);
       
        
        // Il faut enlever des résultat les tracks deja dans la playlist de userLoggedIn
        ArrayList<Track> res = new ArrayList<>();
        
        Iterator<Track> trackIterator = a.getTrackCollection().iterator();
        while(trackIterator.hasNext()){
            Track t = trackIterator.next();
            if (!t.getUsersCollection().contains(LoginBean.getUserLoggedIn())){
                res.add(t);
            }
        }
        
        
        return res;
    }
    


    
    
    
}
