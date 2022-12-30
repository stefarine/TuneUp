package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.database.MockDatabase;
import hec.soar.tuneup.v1.models.User;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{
    
    private String email = "";
    private String password = "";
    private static User currentUser;

    public String userLogsIn() {
        try {
            User user = findUserByEmail(email);
            if (user != null && user.isPasswordCorrect(password)) {
                currentUser = user;
                return "/views/MainMenu/userHomePage.xhtml?faces-redirect=true";
           }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        return "/views/connexion/login_wrongEmailOrPass.xhtml?faces-redirect=true";
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

    public  static User getUserLoggedIn() {
        return currentUser;
    }
    
    public User getTheUserLoggedIn(){
        return currentUser;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public static void setCurrentUser(User currentUser) {
        LoginBean.currentUser = currentUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
