
package hec.soar.tuneup.v1.views;

import hec.soar.tuneup.v1.controllers.LoginController;
import hec.soar.tuneup.v1.controllers.MatchesController;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.models.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Matches {
    
    private static final Scanner userInput = new Scanner(System.in);
    
    static void MatchesPage() throws AlreadyExistsException{
        String choice;
        
        System.out.println("\nEnter:\n"
                    + "\n[b] to return to the User Home Page");
        
        ArrayList<User> matches = MatchesController.getUserMatches(LoginController.getUserLoggedIn());
         int i = 1;
        
         if (matches != null){
            Iterator<User> matchesIterator = matches.iterator();


            while(matchesIterator.hasNext()){
                User um = matchesIterator.next();
                LocalDate today = LocalDate.now();
                int age = User.calculateAge(um.getDate_of_birth(),today);

                System.out.println("["+i+"]"+" User : \""+um.getFirst_name()+""
                        + " - "+age+" years old - "
                        +um.getPersonalDescription()+"\"");
                i++;
            }
         }
        
        choice = userInput.nextLine().toLowerCase();
        ArrayList<String> choicesAvailables;
        choicesAvailables = new ArrayList();
        choicesAvailables.add("b");
        
        if (matches != null){
            for (int j = 1; j <= matches.size(); j++){
                String s = Integer.toString(j);
                choicesAvailables.add(s);

                if (choice.equals(s)){
                    UserProfil.UserProfil(matches.get(j-1));
                }
            }
        }
        
        if (!choicesAvailables.contains(choice)){
            System.out.println("Command \"" + choice + "\" does not exist.\n"
                                    + "please enter a valid command");
            MatchesPage();
        }
        
        
    }
    
    

}
