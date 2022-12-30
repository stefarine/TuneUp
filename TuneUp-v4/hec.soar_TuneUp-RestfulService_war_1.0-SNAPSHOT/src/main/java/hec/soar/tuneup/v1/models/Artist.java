/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hec.soar.tuneup.v1.models;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "Artist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artist.findAll", query = "SELECT a FROM Artist a"),
    @NamedQuery(name = "Artist.findByIdArtist", query = "SELECT a FROM Artist a WHERE a.idArtist = :idArtist"),
    @NamedQuery(name = "Artist.findByName", query = "SELECT a FROM Artist a WHERE a.name = :name")})
public class Artist implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "picture")
    private String picture;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "genres")
    private String genres;
    
    @JoinTable(name = "TrackByArtist", joinColumns = {
        @JoinColumn(name = "artistID", referencedColumnName = "idArtist")}, inverseJoinColumns = {
        @JoinColumn(name = "trackID", referencedColumnName = "idTrack")})
    @ManyToMany
    private Collection<Track> trackCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "idArtist")
    private String idArtist;

    public Artist() {
    }

    public Artist(String idArtist) {
        this.idArtist = idArtist;
    }

    public Artist(String idArtist, String name, String picture, String genres) {
        this.idArtist = idArtist;
        this.name = name;
        this.picture = picture;
        this.genres = genres;
    }

    public String getIdArtist() {
        return idArtist;
    }

    public void setIdArtist(String idArtist) {
        this.idArtist = idArtist;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArtist != null ? idArtist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artist)) {
            return false;
        }
        Artist other = (Artist) object;
        if ((this.idArtist == null && other.idArtist != null) || (this.idArtist != null && !this.idArtist.equals(other.idArtist))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hec.soar.tuneup.v1.models.Artist[ idArtist=" + idArtist + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    @XmlTransient
    public Collection<Track> getTrackCollection() {
        return trackCollection;
    }

    public void setTrackCollection(Collection<Track> trackCollection) {
        this.trackCollection = trackCollection;
    }
    
}
