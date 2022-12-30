package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.models.Artist;
import hec.soar.tuneup.v1.models.Track;
import hec.soar.tuneup.v1.models.Users;
import hec.soar.tuneup.v4.client.PersistenceClient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Named(value = "artistBean")
@SessionScoped
public class artistBean implements Serializable{

    
    public List<Track> getTracksByArtist(String artistName){

        List<Track> allTracks = new ArrayList<>();
        allTracks = PersistenceClient.getInstance().getAllTracks();
        
        List<Track> res = new ArrayList<>();

        for (int i = 0; i<allTracks.size();i++){
            if (allTracks.get(i).getName().equals(artistName)){
                
                Users uLoggedIn = LoginBean.getUserLoggedIn();
                List<Users> uList = allTracks.get(i).getUsersCollection();
                boolean inThePlaylist = false;
                
                for (int j = 0; j<uList.size();j++){
                    if (uList.get(j).getEmail().equals(uLoggedIn.getEmail())){
                        inThePlaylist = true;
                    }
                }
                
                if (!inThePlaylist){
                    res.add(allTracks.get(i)); 
                }
                
                
            }  
        }
                System.out.println("*****************");

        res.forEach(e-> System.out.println(e.getName()));
                        System.out.println("*****************");

        
        return res;
    }
    


    
    
    
}
