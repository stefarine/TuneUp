package hec.soar.tuneup.v1.models;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Track{

    private String idTrack;
    private String name;
    private String preview;
    private String picture;
    private List<Artist> artistCollection;
    private List<Users> usersCollection;

    public Track() {
    }

    public String getIdTrack() {
        return idTrack;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPreview() {
        return preview;
    }
    
    public String getPicture() {
        return picture;
    }
    
    public List<Artist> getArtistCollection() {
        return artistCollection;
    }
    
    public List<Users> getUsersCollection() {
        return usersCollection;
    }
    
    public void setIdTrack(String idTrack) {
        this.idTrack = idTrack;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPreview(String preview) {
        this.preview = preview;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    public void setArtistCollection(List<Artist> artistCollection) {
        this.artistCollection = artistCollection;
    }
    
    public void setUsersCollection(List<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }
    
}
