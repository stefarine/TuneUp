package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.models.Users;
import hec.soar.tuneup.v4.client.PersistenceClient;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Named(value = "updatesBean")
@SessionScoped
public class UpdatesBean implements Serializable {

    
    public void updateSocialMedia(String newValue){

        Users uLoggedIn = LoginBean.getUserLoggedIn();
        uLoggedIn.setSocialMedialink(newValue);
        //em.merge(uLoggedIn);
 
    }
    
    public  String updateFirstName(String newValue){
        
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        uLoggedIn.setFirstName(newValue);
        PersistenceClient.getInstance().updateUser(uLoggedIn);
        
        return "/faces/views/MainMenu/userHomePage.xhtml?faces-redirect=true";
    }
 
    public void updatePersonnalDescription(String newValue){
        
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        uLoggedIn.setPersonalDescription(newValue);
        //em.merge(uLoggedIn);
        
    }

    
}
