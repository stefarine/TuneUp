package hec.soar.tuneup.v1.tests;

import hec.soar.tuneup.v1.controllers.LoginController;
import hec.soar.tuneup.v1.database.MockDatabase;
import org.testng.Assert;

import org.testng.annotations.Test;


public class LoginControllerTest {
    
   
    
    public LoginControllerTest() {
        System.out.println("LoginControllerTest Constructor");
        
    }
    
    
    @Test
    public void testLogsIn(){
        
        LoginController.setEmail("user1@unil.ch");
        LoginController.setPassword("myPassword");
        LoginController.userLogsIn();
        
        
        
        Assert.assertNotNull(LoginController.getUserLoggedIn());
    }
    
}
