package hec.soar.tuneup.v1.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Users {

    
    private String email;
    private String socialMedialink;
    private String firstName;
    private Date dateOfBirth;
    private String password;
    private String personalDescription;
    private String picture;
    private List<Users> usersCollection; // USERS I'VE BEEN LIKED BY
    private List<Users> usersCollection1; // USERS I LIKED

    public Users() {
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getSocialMedialink() {
        return socialMedialink;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getPersonalDescription() {
        return personalDescription;
    }
    
    public String getPicture() {
        return picture;
    }
    
    public List<Users> getUsersCollection() {
        return usersCollection;
    }
    
    public List<Users> getUsersCollection1() {
        return usersCollection1;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    

    public void setSocialMedialink(String socialMedialink) {
        this.socialMedialink = socialMedialink;
    }

    

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    

    public void setPassword(String password) {
        this.password = password;
    }

    

    public void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
    }

    

    public void setPicture(String picture) {
        this.picture = picture;
    }

    

    public void setUsersCollection(List<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    

    public void setUsersCollection1(List<Users> usersCollection1) {
        this.usersCollection1 = usersCollection1;
    }
    
    public int calculateAge(){
        
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
    
}
