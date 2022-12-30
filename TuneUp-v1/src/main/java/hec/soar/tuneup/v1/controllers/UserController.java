package hec.soar.tuneup.v1.controllers;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.models.User;
import java.time.LocalDate;


public class UserController {
    private static String email;
    private static String socialMedia_link;
    private static String first_name;
    private static LocalDate date_of_birth;
    private static String password;
    private static String personalDescription;
    
    
    public static void createAUser() {
        try {
            if (!emailExists()) {
                MockDatabase.getInstance().addUser(new User(email, socialMedia_link, first_name, date_of_birth, password, personalDescription));
                System.out.println("Account successfully created !");
                
                User u =MockDatabase.getInstance().getUsers().get(email);
                MockDatabase.getInstance().createLikeList(u);
                MockDatabase.getInstance().createPlayList(u);
            }
        } catch (AlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    protected static boolean emailExists() throws AlreadyExistsException {
        
        MockDatabase.getInstance().getUsers().keySet();
        
        for (String e : MockDatabase.getInstance().getUsers().keySet()) {
            if (e.equals(email)) {
                throw new AlreadyExistsException("The email " + email + "is already in use.");
            }
        }
        return false;
    }
    
    
    // setters
    
    public static void setEmail(String email){
        UserController.email = email;
    }
    
    public static void setSocialMedia_link(String smL){
        UserController.socialMedia_link = smL;
    }
    
    public static void setFirstname(String fn){
        UserController.first_name = fn;
    }
    
    public static void setDate_of_birth(LocalDate dob){
        UserController.date_of_birth = dob;
    }
    
    public static void setPassword(String password){
        UserController.password = password;
    }
    
    public static void setPersonalDescription(String pd){
        UserController.personalDescription = pd;
    }
    
    // getters
    
    public static String getEmail(){
        return email;
    }
    
    public static String getSocialMedia_link(){
        return socialMedia_link;
    }
    
    public static String getFirstName(){
        return first_name;
    }
    
    public static LocalDate getDate_of_birth(){
        return date_of_birth;
    }
    
    public static String getPassword(){
        return password;
    }
    
    public static String getPersonalDescription(){
        return personalDescription;
    }
    
    
    
}
