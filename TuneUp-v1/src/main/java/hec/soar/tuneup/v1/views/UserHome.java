package hec.soar.tuneup.v1.views;

import hec.soar.tuneup.v1.controllers.ArtistController;
import hec.soar.tuneup.v1.controllers.TrackController;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import java.util.Scanner;

public class UserHome {
    
    private static final Scanner userInput = new Scanner(System.in);
    
    public static void userHomePage() throws DoesNotExistException, AlreadyExistsException {
        String choice;
        do{
            System.out.println("\nEnter:\n"
                    + "\n[q] to log out and quit the application"
                    + "\n[1] to search for a track"
                    + "\n[2] to search for an artist"
                    + "\n[3] to consult your playlist"
                    + "\n[4] to search for new matches"
                    + "\n[5] to consult your matches"
                    + "\n[6] to update your profile");
            choice = userInput.nextLine().toLowerCase();
            
            
            switch (choice) {
                case "1":
                    System.out.println("Enter the track's name : ");
                    String trackName = userInput.nextLine().toLowerCase();
                    try{
                        TrackController.getTrackByName(trackName);
                        if (TrackController.getTrack() != null){
                            
                            TrackPage.trackPage();
                        }
                    }
                    catch (DoesNotExistException e){
                        System.out.println(e.getMessage());
                    }
                    break;  
                    
                case "2": 
                    System.out.println("Enter the artist's name : ");
                    String artistName = userInput.nextLine().toLowerCase();
                    try{
                        ArtistController.getArtistByName(artistName);
                        if (ArtistController.getArtist() != null){
                            
                            ArtistPage.ArtistPage();
                            
                        }
                    }
                    catch (DoesNotExistException e){
                        System.out.println(e.getMessage());
                    }
                    break; 
                case "3": 
                    PlaylistPage.PlaylistPage();
                    break;
                case "4": 
                    NewMatches.NewMatches();
                    break; 
                case "5": 
                    Matches.MatchesPage();
                    break;
                case "6": 
                    Update.UpdatePage();
                    break;     
                case "q":
                    System.out.println("Quitting...");
                    System.exit(0);
                default:
                    System.out.println("Command \"" + choice + "\" does not exist.\n"
                            + "please enter a valid command");
                    break;
            }
            
        } while (!choice.equals("q"));
    }
    
}
