package hec.soar.tuneup.v1.controllers;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.WrongFormatException;
import hec.soar.tuneup.v1.models.User;
import java.time.LocalDate;

public class UpdatesController {
    
    
    
    public static void updateSocialMedia(String newValue, User uLoggedIn){
        MockDatabase db = MockDatabase.getInstance();
        uLoggedIn.setSocialMedia_link(newValue);
        db.getUsers().put(uLoggedIn.getEmail(), uLoggedIn);     
    }
    
    public static void updateFirstName(String newValue, User uLoggedIn){
        MockDatabase db = MockDatabase.getInstance();
        uLoggedIn.setFirst_name(newValue);
        db.getUsers().put(uLoggedIn.getEmail(), uLoggedIn);
    }
    
    public static void updateDateOfBirth(LocalDate newValue, User uLoggedIn){
        MockDatabase db = MockDatabase.getInstance();
        uLoggedIn.setDate_of_birth(newValue);
        db.getUsers().put(uLoggedIn.getEmail(), uLoggedIn);
    }
    
    public static void updatePassword(String newValue, User uLoggedIn){
        MockDatabase db = MockDatabase.getInstance();
        uLoggedIn.setPassword(newValue);
        db.getUsers().put(uLoggedIn.getEmail(), uLoggedIn);
    }
    
    public static void updatePersonnalDescription(String newValue, User uLoggedIn){
        MockDatabase db = MockDatabase.getInstance();
        uLoggedIn.setPersonalDescription(newValue);
        db.getUsers().put(uLoggedIn.getEmail(), uLoggedIn);
    }
    
}
