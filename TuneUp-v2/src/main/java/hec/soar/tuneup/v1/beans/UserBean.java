package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.models.User;
import java.io.Serializable;
import java.time.LocalDate;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    private String email;
    private String socialMedia_link;
    private String firstName;
    private LocalDate date_of_birth;
    private String password;
    private String personalDescription;
    private String picture_url;
    
    
    public String createAUser() {
        try {
            if (!emailExists()) {
                MockDatabase.getInstance().addUser(new User(email, socialMedia_link, firstName, date_of_birth, password, personalDescription,"https://i.ibb.co/Kmgwh82/pexels-elle-hughes-1680172.jpg"));
                System.out.println("Account successfully created !");
                
                User u =MockDatabase.getInstance().getUsers().get(email);
                MockDatabase.getInstance().createLikeList(u);
                MockDatabase.getInstance().createPlayList(u);
            }
        } catch (AlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }
        return "/views/MainMenu/index.xhtml?faces-redirect=true";
    }
    
    protected boolean emailExists() throws AlreadyExistsException {
        
        MockDatabase.getInstance().getUsers().keySet();
        
        for (String e : MockDatabase.getInstance().getUsers().keySet()) {
            if (e.equals(email)) {
                throw new AlreadyExistsException("The email " + email + "is already in use.");
            }
        }
        return false;
    }
    
    
    // setters
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setSocialMedia_link(String smL){
        this.socialMedia_link = smL;
    }
    
    public void setFirstName(String fn){
        this.firstName = fn;
    }
    
    public void setDate_of_birth(LocalDate dob){
        this.date_of_birth = dob;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setPersonalDescription(String pd){
        this.personalDescription = pd;
    }
    
    public void setPicture_url(String url){
        this.picture_url = url;
    }
    
    // getters
    
    public String getEmail(){
        return email;
    }
    
    public String getSocialMedia_link(){
        return socialMedia_link;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public LocalDate getDate_of_birth(){
        return date_of_birth;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getPersonalDescription(){
        return personalDescription;
    }
    
    public String getPicture_url(){
        return picture_url;
    }
    
    
}
