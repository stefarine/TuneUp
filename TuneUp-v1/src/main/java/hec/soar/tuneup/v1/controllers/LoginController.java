package hec.soar.tuneup.v1.controllers;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.models.User;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import java.util.Map;

public class LoginController {
    
    private static String email = "";
    private static String password = "";
    private static User currentUser;

    public static void userLogsIn() {
        try {
            User user = findUserByEmail(email);
            if (user != null && user.isPasswordCorrect(password)) {
                currentUser = user;
           }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected static User findUserByEmail(String email) throws DoesNotExistException {
        MockDatabase db = MockDatabase.getInstance();
        Map<String, User> users = db.getUsers();
        
        if (users.containsKey(email)){
            return users.get(email);
        }
    
    throw new DoesNotExistException("This email > "+email+" does not exist.");
    }
    
    public static void userLogsout() {
        currentUser = null;
    }

    public static User getUserLoggedIn() {
        return currentUser;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUsername() {
        return email;
    }

    public static void setCurrentUser(User currentUser) {
        LoginController.currentUser = currentUser;
    }

    public static void setPassword(String password) {
        LoginController.password = password;
    }

    public static void setEmail(String email) {
        LoginController.email = email;
    }

}
