package hec.soar.tuneup.v1.tests;



import hec.soar.tuneup.v1.controllers.LoginController;
import hec.soar.tuneup.v1.controllers.MatchesController;
import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.exceptions.AlreadyExistsException;
import hec.soar.tuneup.v1.models.User;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MatchesControllerTest {
    
     MockDatabase db = MockDatabase.getInstance();
    
    public MatchesControllerTest() {
        System.out.println("MatchesController Constructor");
    }
    
    @Test
    public void getUserMatchesTest(){
        MatchesController.start();
        
        LoginController.setEmail("user1@unil.ch");
        LoginController.setPassword("myPassword");
        LoginController.userLogsIn();
        User u = LoginController.getUserLoggedIn();
        
        User u4 = db.getUsers().get("user4@unil.ch");
        
        
        Assert.assertTrue(MatchesController.getUserMatches(u).contains(u4));
    }
    
    @Test
    public void addUserToMatchesListTest() throws AlreadyExistsException{
        MatchesController.start();
        MockDatabase  db = MockDatabase.getInstance();
        
        User uToMatch = db.getUsers().get("user2@unil.ch");
        User userThatMatch = db.getUsers().get("user1@unil.ch");
        LoginController.setCurrentUser(userThatMatch);
        
        MatchesController.addUserToMatchesList(uToMatch);
        
        boolean b = MatchesController.getUserMatches(userThatMatch).contains(uToMatch);
        
        Assert.assertTrue(b);
        
    }
    
    @Test
    public void deleteUserfromMatchesListTest() throws AlreadyExistsException{
        MatchesController.start();
        MockDatabase  db = MockDatabase.getInstance();
        
        User uToRemove = db.getUsers().get("user2@unil.ch");
        User userThatRemove = db.getUsers().get("user3@unil.ch");
        LoginController.setCurrentUser(userThatRemove);
        
        MatchesController.removeUserFromMatchesList(uToRemove);
        
        boolean b = MatchesController.getUserMatches(userThatRemove).contains(uToRemove);
        
        Assert.assertFalse(b);
        
    }
    
    
    
    
}
