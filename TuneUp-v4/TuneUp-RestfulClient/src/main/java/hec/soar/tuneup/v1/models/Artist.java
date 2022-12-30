package hec.soar.tuneup.v1.models;

import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Artist {
    
    private String idArtist;
    private String name;
    private String picture;
    private String genres;
    private Collection<Track> trackCollection;

    public Artist() {
    }

    public String getIdArtist() {
        return idArtist;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPicture() {
        return picture;
    }
    
    public String getGenres() {
        return genres;
    }
    
    public Collection<Track> getTrackCollection() {
        return trackCollection;
    }
    
    public void setIdArtist(String idArtist) {
        this.idArtist = idArtist;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    public void setGenres(String genres) {
        this.genres = genres;
    }
    
    public void setTrackCollection(Collection<Track> trackCollection) {
        this.trackCollection = trackCollection;
    }

}
