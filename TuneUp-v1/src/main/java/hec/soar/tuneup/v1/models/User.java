
package hec.soar.tuneup.v1.models;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private String email;
    private String socialMedia_link;
    private String first_name;
    private LocalDate  date_of_birth;
    private String password;
    private String personalDescription;
    
    // constructor
    public User(String email, String socialMedia_link,
                  String first_name, LocalDate date_of_birth, 
                  String password, String personalDescription ){
    
        this.email = email;
        this.socialMedia_link = socialMedia_link;
        this.first_name = first_name;
        this.date_of_birth = date_of_birth;
        this.password = Integer.toString(password.hashCode());
        this.personalDescription = personalDescription;
    }
    
    // getters
    public String getEmail(){
        return email;
    }
    
    public String getSocialMedia_link(){
        return socialMedia_link;
    }
    
    public String getFirst_name(){
        return first_name;
    }
    
    public LocalDate getDate_of_birth(){
        return date_of_birth;
    }
    
    public String getPassword(){
        return password;
        // return the password hashed
    }
    
    public String getPersonalDescription(){
        return personalDescription;
    }
    
   
    // setters
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setSocialMedia_link(String socialMedia_link){
        this.socialMedia_link = socialMedia_link;
    }
    
    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }
    
    public void setDate_of_birth(LocalDate date_of_birth){
        this.date_of_birth = date_of_birth;
    }
    
    public void setPassword(String password){
        this.password = Integer.toString(password.hashCode());
    }
    
    public void setPersonalDescription(String personalDescription){
        this.personalDescription = personalDescription;
    }
    
    // Class Methodes
    public boolean isPasswordCorrect(String password) {
        // check if the HASH of the password is correct
        return Integer.toString(password.hashCode()).equals(this.password);
    }
    
    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
    
    @Override
    public String toString() {
        return "Email: " + this.email
                + "\nSocialMediaLink: " + this.socialMedia_link
                + "\nFirst name: " + this.first_name
                + "\nDate Of Birth: " + this.date_of_birth
                + "\nPassword hash: " + this.password
                + "\nPersonal Description: " + this.personalDescription;
        
    }
    
}
