package hec.soar.tuneup.v1.views;

import hec.soar.tuneup.v1.controllers.LoginController;
import hec.soar.tuneup.v1.controllers.PlaylistController;
import hec.soar.tuneup.v1.controllers.TrackController;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import hec.soar.tuneup.v1.models.Track;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PlaylistPage {
     private static final Scanner userInput = new Scanner(System.in);
     
     static void PlaylistPage() throws AlreadyExistsException, DoesNotExistException{
        String choice;
        System.out.println("\nEnter:\n"
                    + "\n[b] to return to the User Home Page");
        
        ArrayList<Track> userPlaylist;
        userPlaylist = PlaylistController.getUserPlaylist(LoginController.getUserLoggedIn());
        
        int i = 1;
        if(userPlaylist != null){
            Iterator<Track> trackIterator = userPlaylist.iterator();
            
            while (trackIterator.hasNext()){
                Track track = trackIterator.next();
                System.out.println("["+i+"]"+" Track : \""+track.getName()+"\"");
                i++;
            }
        }
        
        
        
        choice = userInput.nextLine().toLowerCase();
        ArrayList<String> choicesAvailables;
        choicesAvailables = new ArrayList();
        choicesAvailables.add("b");
         
        if(userPlaylist != null){
            for (int j = 1; j <= userPlaylist.size(); j++){
                String s = Integer.toString(j);
                choicesAvailables.add(s);

                if (choice.equals(s)){
                    TrackController.setTrack(userPlaylist.get(j-1));
                    TrackPage.trackPage();
                }
            }
        }
        
        if (!choicesAvailables.contains(choice)){
            System.out.println("Command \"" + choice + "\" does not exist.\n"
                                    + "please enter a valid command");
            PlaylistPage();
        }
        
        
     }
     
}
