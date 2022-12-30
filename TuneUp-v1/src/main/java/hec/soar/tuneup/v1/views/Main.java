package hec.soar.tuneup.v1.views;

import hec.soar.tuneup.v1.controllers.ArtistController;
import hec.soar.tuneup.v1.controllers.LoginController;
import hec.soar.tuneup.v1.controllers.MatchesController;
import hec.soar.tuneup.v1.controllers.PlaylistController;
import hec.soar.tuneup.v1.controllers.TrackController;
import hec.soar.tuneup.v1.controllers.UserController;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import hec.soar.tuneup.v1.exceptions.WrongFormatException;

import hec.soar.tuneup.v1.views.UserHome;
import java.time.LocalDate;


import java.util.Scanner;

public class Main {
    
    private static final Scanner userInput = new Scanner(System.in);
    
    public static void main(String[] args) throws DoesNotExistException, AlreadyExistsException {
        TrackController.start();
        ArtistController.start();
        PlaylistController.start();
        MatchesController.start();
        mainPage();
    }
    
    
    private static void mainPage() throws DoesNotExistException, AlreadyExistsException{
        String choice, mail, socialMedia_link;
        String first_name, password, personalDescription;
        LocalDate date_of_birth;
        do{
            System.out.println("\nEnter:\n"
                    + "\n[q]to quit the application"
                    + "\n[1]to login"
                    + "\n[2]to create a user account");
            choice = userInput.nextLine().toLowerCase();
            
            switch (choice) {
                case "1":
                    System.out.println("Enter your email :");
                    String email = userInput.nextLine().toLowerCase();
                    System.out.println("Enter your password :");
                    String pw = userInput.nextLine();
                    LoginController.setEmail(email);
                    LoginController.setPassword(pw);
                    LoginController.userLogsIn();
                    if (LoginController.getUserLoggedIn() != null){
                        UserHome.userHomePage();
                    }                 
                    break;
                case "2": 
                    System.out.println("Enter your email :");
                    mail = userInput.nextLine();
                    System.out.println("Enter your password :");
                    password = userInput.nextLine();
                    System.out.println("Enter your first name :");
                    first_name = userInput.nextLine();
                    System.out.println("Enter your date of birth [DD/MM/YYYY] :");
                    String fullDate = userInput.nextLine();
                    
                    try{
                        String[] parts = fullDate.split("/");
                        
                        if (parts.length!=3){
                            System.out.println("Wrong Format, Please try again.");
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
                        date_of_birth = dateToInt(parts[0],parts[1], parts[2]);
                        UserController.setDate_of_birth(date_of_birth);
                    }
                    catch(WrongFormatException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    
                    System.out.println("Enter a link to your profile on the social network of your choice :");
                    socialMedia_link = userInput.nextLine();
                    System.out.println("Enter a personal description :");
                    personalDescription = userInput.nextLine();

                    UserController.setEmail(mail);
                    UserController.setSocialMedia_link(socialMedia_link);
                    UserController.setFirstname(first_name);
                    UserController.setPassword(password);
                    UserController.setPersonalDescription(personalDescription);
                    UserController.createAUser();

                    break;
                case "q":
                    System.out.println("Quitting...");
                    break;
                default:
                    System.out.println("Command \"" + choice + "\" does not exist.\n"
                            + "please enter a valid command");
                    break;
            }
            
        } while (!choice.equals("q"));
        
        
        
    }
    
    static LocalDate dateToInt(String d,String m, String y) throws WrongFormatException{
        
        
        try{
        int day = Integer.parseInt(d);
        int month = Integer.parseInt(m);
        int year = Integer.parseInt(y);
        LocalDate date_of_birth = LocalDate.of(year, month, day);
        return date_of_birth;
        }
        catch(NumberFormatException e){
            
            throw new WrongFormatException("Wrong Format, Please try again.");
        
        }
 
    }
    
    
    
}


