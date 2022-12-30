package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.WrongFormatException;
import hec.soar.tuneup.v1.models.User;
import java.io.Serializable;
import java.time.LocalDate;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value = "updatesBean")
@SessionScoped
public class UpdatesBean implements Serializable {
    
    
    
    public  void updateSocialMedia(String newValue, User uLoggedIn){
        MockDatabase db = MockDatabase.getInstance();
        uLoggedIn.setSocialMedia_link(newValue);
        db.getUsers().put(uLoggedIn.getEmail(), uLoggedIn);     
    }
    
    public  String updateFirstName(String newValue, User uLoggedIn){
        MockDatabase db = MockDatabase.getInstance();
        uLoggedIn.setFirst_name(newValue);
        db.getUsers().put(uLoggedIn.getEmail(), uLoggedIn);
        
        return "/faces/views/MainMenu/userHomePage.xhtml?faces-redirect=true";
    }
    
    public void updateDateOfBirth(LocalDate newValue, User uLoggedIn){
        MockDatabase db = MockDatabase.getInstance();
        uLoggedIn.setDate_of_birth(newValue);
        db.getUsers().put(uLoggedIn.getEmail(), uLoggedIn);
    }
    
    public void updatePassword(String newValue, User uLoggedIn){
        MockDatabase db = MockDatabase.getInstance();
        uLoggedIn.setPassword(newValue);
        db.getUsers().put(uLoggedIn.getEmail(), uLoggedIn);
    }
    
    public void updatePersonnalDescription(String newValue, User uLoggedIn){
        MockDatabase db = MockDatabase.getInstance();
        uLoggedIn.setPersonalDescription(newValue);
        db.getUsers().put(uLoggedIn.getEmail(), uLoggedIn);
    }
    
    public void updateAll(){
        
        
    }
    
}
