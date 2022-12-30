
package hec.soar.tuneup.v1.views;

import hec.soar.tuneup.v1.controllers.LoginController;
import hec.soar.tuneup.v1.controllers.MatchesController;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.models.User;
import java.time.LocalDate;
import java.util.Scanner;

public class UserProfil {
    
    private static final Scanner userInput = new Scanner(System.in);
    
    static void UserProfil(User u) throws AlreadyExistsException{
        boolean matched = false;
        String choice;
        
        if (MatchesController.getUserMatches(LoginController.getUserLoggedIn()).contains(u)){   
            matched = true;
        }
    
        System.out.println("This is "+u.getFirst_name()+"'s profile :\n");
        System.out.println(u.getFirst_name());
        
        LocalDate today = LocalDate.now();
        int age = User.calculateAge(u.getDate_of_birth(), today);
        
        System.out.println(age+" years old");
        System.out.println(u.getPersonalDescription());
        
        if(matched){
            System.out.println(u.getSocialMedia_link());
            System.out.println(u.getEmail());
        }
        
        System.out.println("\nEnter : \n\n"
                + "[b] to return to the User Home Page");
        
        if(matched){
            System.out.println("[1] to remove this profile from your matches");
        }
        else{
            System.out.println("[1] to like this profile");
        }
        
        choice = userInput.nextLine().toLowerCase();
        
        if (choice.equals("1")){
            if(matched){
                MatchesController.removeUserFromMatchesList(u);
            }
            else{
                MatchesController.addUserToMatchesList(u);
            }
        }
        else if(!choice.equals("b") && !choice.equals("1")){
            System.out.println("Command \"" + choice + "\" does not exist.\n"
                                    + "please enter a valid command");
            UserProfil(u);
        }
        
        
    }
}
