package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.models.Users;
import hec.soar.tuneup.v4.client.PersistenceClient;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    
    private String email = "";
    private String socialMedia_link = "";
    private String firstName = "";
    private String date_of_birth = "";
    private String password = "";
    private String personalDescription = "";
    private String picture_url = "";
    
    public String createAUser() {
                System.out.print("YOOOOOOOOOOOO");

        Users newUser = new Users();
        newUser.setEmail(email);
        newUser.setSocialMedialink(socialMedia_link);
        newUser.setFirstName(firstName);
        newUser.setPassword(password);
        newUser.setPersonalDescription(personalDescription);
        
        //changer photo par défaut
        newUser.setPicture("https://i.ibb.co/Kmgwh82/pexels-elle-hughes-1680172.jpg");
        

        String[] tokens = date_of_birth.split("/");
        int day = Integer.valueOf(tokens[0]);
        int month = Integer.valueOf(tokens[1]);
        int year = Integer.valueOf(tokens[2]);
        
        Date date = new GregorianCalendar(year,month-1,day).getTime();
        newUser.setDateOfBirth(date);
        
        System.out.println(newUser);
        
        PersistenceClient.getInstance().createUser(newUser);
        
        this.email="";
        this.socialMedia_link="";
        this.firstName="";
        this.password="";
        this.personalDescription="";
        this.picture_url="";
        this.date_of_birth="";
        
        return "/views/MainMenu/index.xhtml?faces-redirect=true";
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
    
    public void setDate_of_birth(String dob){
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
    
    public String getDate_of_birth(){
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
