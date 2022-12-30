
package hec.soar.tuneup.v1.views;

import hec.soar.tuneup.v1.controllers.PlaylistController;
import hec.soar.tuneup.v1.controllers.TrackController;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import java.util.Scanner;

public class TrackPage {
    
    private static final Scanner userInput = new Scanner(System.in);
    
    public static void trackPage() throws AlreadyExistsException, DoesNotExistException{
        String choice;
        String trackName = TrackController.getTrack().getName();
        String artistName = TrackController.getArtist().getName();
        String preview_url = TrackController.getTrack().getPreview_url();
        
        System.out.println("\n#########################\n   "+trackName);
        System.out.println("        "+artistName);
        System.out.println("#########################\n"
                + "Copy and paste in your web browser the link below to get a preview");
        System.out.println(preview_url);
        do{
   
        if (!PlaylistController.containsTrack(TrackController.getTrack())){
            System.out.println("\nEnter:\n"
                        + "\n[b] to return to the User Home Page"
                        + "\n[1] add this track to my playlist");
        }
        else {
            System.out.println("\nEnter:\n"
                        + "\n[b] to return to the User Home Page"
                        + "\n[1] delete this track from the playlist");
        }
        choice = userInput.nextLine().toLowerCase();
        
        
            switch(choice){
                case "b":
                    break;
                case "1":
                    if (!PlaylistController.containsTrack(TrackController.getTrack())){
                        try{
                            PlaylistController.addTrackToPlaylist(TrackController.getTrack());
                            System.out.println("Track successfully added to your playlist");
                        } catch ( AlreadyExistsException ex){
                            System.out.println(ex.getMessage());
                        }
                    }
                    else {
                        try{
                            PlaylistController.removeTrackFromPlaylist(TrackController.getTrack());
                            System.out.println("Track successfully deleted from your playlist");
                        } catch ( DoesNotExistException ex){
                            System.out.println(ex.getMessage());
                        } 
                    }
                    break;
                default:
                        System.out.println("Command \"" + choice + "\" does not exist.\n"
                                + "please enter a valid command");
                        break; 
            }
        }while(!choice.equals("b") && !choice.equals("1"));
        // J'ai arreter là ... mais pas obligé de continuer sur cette view
        
        
        
        
        

    }
}
