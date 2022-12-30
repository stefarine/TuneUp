/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hec.soar.tuneup.v1.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stephane
 */
@Entity
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findBySocialMedialink", query = "SELECT u FROM Users u WHERE u.socialMedialink = :socialMedialink"),
    @NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "Users.findByDateOfBirth", query = "SELECT u FROM Users u WHERE u.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "socialMedia_link")
    private String socialMedialink;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "personalDescription")
    private String personalDescription;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "picture")
    private String picture;
    @JoinTable(name = "likeUser", joinColumns = {
        @JoinColumn(name = "userLiked_id", referencedColumnName = "email")}, inverseJoinColumns = {
        @JoinColumn(name = "userWhoLikes_id", referencedColumnName = "email")})
    @ManyToMany
    private Collection<Users> usersCollection; // USERS I'VE BEEN LIKED BY
    
    
    @ManyToMany(mappedBy = "usersCollection")
    private Collection<Users> usersCollection1; // USERS I LIKED

    public Users() {
    }
    
    public boolean isPasswordCorrect(String password) {
        boolean correct = password.equals(this.password);
        //System.out.println("[v3-debug] isPasswordCorrect: "+correct);
        return correct;
    }

    public Users(String email) {
        this.email = email;
    }

    public Users(String email, String socialMedialink, String firstName, Date dateOfBirth, String password, String personalDescription, String picture) {
        this.email = email;
        this.socialMedialink = socialMedialink;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.personalDescription = personalDescription;
        this.picture = picture;
    }
    


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocialMedialink() {
        return socialMedialink;
    }

    public void setSocialMedialink(String socialMedialink) {
        this.socialMedialink = socialMedialink;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonalDescription() {
        return personalDescription;
    }

    public void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection1() {
        return usersCollection1;
    }

    public void setUsersCollection1(Collection<Users> usersCollection1) {
        this.usersCollection1 = usersCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }
    
    public int calculateAge() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        
        Date now = new Date(System.currentTimeMillis());
        //System.out.println("[v3-debug] DateNow: "+formatter.format(now));
        Date birthDate = this.dateOfBirth;
        //System.out.println("[v3-debug] DateOfBirth: "+formatter.format(birthDate));
        
        int d1 = Integer.parseInt(formatter.format(birthDate));                            
        int d2 = Integer.parseInt(formatter.format(now)); 
        int age = (d2 - d1) / 10000;  
        
        //System.out.println("[v3-debug] Age: "+age);
        
        return age;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Email: " + this.email
                + "\nSocialMediaLink: " + this.socialMedialink
                + "\nFirst name: " + this.firstName
                + "\nDate Of Birth: " + this.dateOfBirth
                + "\nPassword hash: " + this.password
                + "\nPersonal Description: " + this.personalDescription;
    }
    
}
