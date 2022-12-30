package hec.soar.tuneup.v1.views;

import hec.soar.tuneup.v1.controllers.LoginController;
import hec.soar.tuneup.v1.controllers.UpdatesController;
import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.WrongFormatException;
import hec.soar.tuneup.v1.models.User;
import static hec.soar.tuneup.v1.views.Main.dateToInt;
import java.time.LocalDate;
import java.util.Scanner;


public class Update {
    private static final Scanner userInput = new Scanner(System.in);
    
    static void UpdatePage(){
        String choice;

        String emailConnected = LoginController.getUserLoggedIn().getEmail();
        
        do{
            System.out.println("\nYou're connected on "+emailConnected+" account.");
            System.out.println("\nEnter : \n"
                    + "\n[b] to return to the User Home Page"
                    + "\n[1] to update your social media link"
                    + "\n[2] to update your first name"
                    + "\n[3] to update your date of birth"
                    + "\n[4] to update your password"
                    + "\n[5] to update your personal description");
            choice = userInput.nextLine().toLowerCase();
        
            String newValue;
            MockDatabase db = MockDatabase.getInstance();
            User uLoggedIn = LoginController.getUserLoggedIn();
            switch(choice){
                case "b":
                    break;
                case "1":
                    System.out.println("Enter your new social media link :");
                    newValue = userInput.nextLine();
                    UpdatesController.updateSocialMedia(newValue,uLoggedIn);
                    break;
                case "2":
                    System.out.println("Enter your new first name :");
                    newValue = userInput.nextLine();
                    UpdatesController.updateFirstName(newValue, uLoggedIn);
                    break;
                case "3":
                    
                    System.out.println("Enter your new date of birth :");
                    newValue = userInput.nextLine();
                    
                    try{
                        String[] parts = newValue.split("/");
                        
                        if (parts.length!=3){
                            System.out.println("Wrong Format.");
                            break;
                        }
                        if (Integer.parseInt(parts[1]) > 12){
                            System.out.println("Months value can't be bigger than 12.");
                            break;
                        }
                        if (Integer.parseInt(parts[0]) > 31){
                            System.out.println("Days value can't be bigger than 31.");
                            break;
                        }
                        if (Integer.parseInt(parts[1]) <= 0){
                            System.out.println("Months value can't be smaller than 1.");
                            break;
                        }
                        if (Integer.parseInt(parts[0]) <= 0){
                            System.out.println("Days value can't be smaller than 1.");
                            break;
                        }
                        if (Integer.parseInt(parts[2]) <= 0){
                            System.out.println("Years value can't be smaller than 1.");
                            break;
                        }
                        LocalDate date_of_birth = dateToInt(parts[0],parts[1], parts[2]);
                        
                        UpdatesController.updateDateOfBirth(date_of_birth, uLoggedIn);
                    }
                    catch(WrongFormatException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    
                    break;
                case "4":
                    System.out.println("Enter your new password :");
                    newValue = userInput.nextLine();
                    
                    UpdatesController.updatePassword(newValue, uLoggedIn);
                    break;
                case "5":
                    System.out.println("Enter your new personal description :");
                    newValue = userInput.nextLine();
                    UpdatesController.updatePersonnalDescription(newValue, uLoggedIn);
                    break;
                default:
                    System.out.println("Command \"" + choice + "\" does not exist.\n"
                            + "please enter a valid command");
                    break;

            }
        }while(!choice.equals("b") && 
                !choice.equals("1") &&
                !choice.equals("2") &&
                !choice.equals("3") &&
                !choice.equals("4") &&
                !choice.equals("5"));
        
        
    }
}
