package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.models.Users;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{
    
    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
    
    private String email = "";
    private String password = "";
    private static Users currentUser;

    public String userLogsIn() {
        try {
            Users user = findUserByEmail(email);
            if (user != null && user.isPasswordCorrect(password)) {
                currentUser = user;
                return "/views/MainMenu/userHomePage.xhtml?faces-redirect=true";
           }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        return "/views/connexion/login_wrongEmailOrPass.xhtml?faces-redirect=true";
    }

    protected  Users findUserByEmail(String email) throws DoesNotExistException {
        Query query = em.createNamedQuery("Users.findByEmail", Users.class);
        List<Users> users = query.setParameter("email", email).getResultList();
        if (users.size() > 0) {
            return users.get(0);
        }
    throw new DoesNotExistException("This email > "+email+" does not exist.");
    }
    
    public static void userLogsout() {
        currentUser = null;
    }

    public  static Users getUserLoggedIn() {
        return currentUser;
    }
    
    public Users getTheUserLoggedIn(){
        return currentUser;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public static void setCurrentUser(Users currentUser) {
        LoginBean.currentUser = currentUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
