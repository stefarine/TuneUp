package hec.soar.tuneup.v1.tests;

import hec.soar.tuneup.v1.controllers.ArtistController;
import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import hec.soar.tuneup.v1.models.Artist;
import hec.soar.tuneup.v1.models.Track;
import java.util.ArrayList;
import java.util.Map;
import org.testng.Assert;

import org.testng.annotations.Test;


public class ArtistControllerTest {
    
    MockDatabase db = MockDatabase.getInstance();
    
    public ArtistControllerTest(){
        System.out.println("ArtistControllerTest Constructor");
        
        //MockDatabase db = MockDatabase.getInstance();
        
        /*for ( var u : db.getUsers().entrySet()){
            System.out.println(u.getValue()+"\n\n");
        }*/
    }
    
    @Test
    public void testGetAllArtists(){
        Map<String, Artist> artists = ArtistController.getAllArtists();
        
        Assert.assertTrue(artists instanceof Map<String, Artist>);
        Assert.assertNotNull(artists);
    }
    
    @Test
    public void testSetArtist() throws DoesNotExistException{
        ArtistController.setArtist(new Artist("idArtist002","Michael Jackson",
            "https://media.vogue.fr/photos/5f23e65ba806c0ec57bc13e6/4:3/w_755,h_566,c_limit/thriller-758x758.jpg",
            new String[]{"Pop","Soul","Funk","Rock","Disco"}));
        
        Assert.assertTrue("Michael Jackson".equals(ArtistController.getArtist().getName()));
        Assert.assertFalse("Bob Marley".equals(ArtistController.getArtist().getName()));
    }
    
    @Test
    public void testGetArtist() throws DoesNotExistException{
        ArtistController.setArtist(new Artist("idArtist002","Michael Jackson",
            "https://media.vogue.fr/photos/5f23e65ba806c0ec57bc13e6/4:3/w_755,h_566,c_limit/thriller-758x758.jpg",
            new String[]{"Pop","Soul","Funk","Rock","Disco"}));
        
        Assert.assertNotNull(ArtistController.getArtist());
    }
    
    @Test
    public void testGetArtistTracks() throws DoesNotExistException{
        ArtistController.setArtist(db.getArtists().get("idArtist002"));
        
        ArrayList<Track> artistTracks = ArtistController.getArtistTracks();
        
        Assert.assertTrue(artistTracks instanceof ArrayList<Track>);
        Assert.assertNotNull(artistTracks);
    }
    
    @Test
    public void testGetArtistByName() throws DoesNotExistException{
        ArtistController.getArtistByName("Nekfeu");
        
        Assert.assertNotNull(ArtistController.getArtist());
    }
    
}
