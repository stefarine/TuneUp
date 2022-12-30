/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hec.soar.tuneup.v1.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stephane
 */
@Entity
@Table(name = "Track")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Track.findAll", query = "SELECT t FROM Track t"),
    @NamedQuery(name = "Track.findByIdTrack", query = "SELECT t FROM Track t WHERE t.idTrack = :idTrack"),
    @NamedQuery(name = "Track.findByName", query = "SELECT t FROM Track t WHERE t.name = :name")})
public class Track implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "preview")
    private String preview;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "picture")
    private String picture;
    
    @ManyToMany(mappedBy = "trackCollection")
    private List<Artist> artistCollection;
    
    @JoinTable(name = "inThePlaylist", joinColumns = {
        @JoinColumn(name = "trackID", referencedColumnName = "idTrack")}, inverseJoinColumns = {
        @JoinColumn(name = "userID", referencedColumnName = "email")})
    @ManyToMany
    private List<Users> usersCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "idTrack")
    private String idTrack;

    public Track() {
    }

    public Track(String idTrack) {
        this.idTrack = idTrack;
    }

    public Track(String idTrack, String name, String preview, String picture) {
        this.idTrack = idTrack;
        this.name = name;
        this.preview = preview;
        this.picture = picture;
    }

    public String getIdTrack() {
        return idTrack;
    }

    public void setIdTrack(String idTrack) {
        this.idTrack = idTrack;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrack != null ? idTrack.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Track)) {
            return false;
        }
        Track other = (Track) object;
        if ((this.idTrack == null && other.idTrack != null) || (this.idTrack != null && !this.idTrack.equals(other.idTrack))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hec.soar.tuneup.v1.models.Track[ idTrack=" + idTrack + " ]";
    }


   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    
    

    
    public List<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(List<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }
    
    public List<Artist> getArtistCollection() {
        return artistCollection;
    }

    public void setArtistCollection(List<Artist> artistCollection) {
        this.artistCollection = artistCollection;
    }
    
    
    
}
