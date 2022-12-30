package hec.soar.tuneup.v1.beans;

import hec.soar.tuneup.v1.models.Users;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Named(value = "updatesBean")
@SessionScoped
public class UpdatesBean implements Serializable {
    
    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;
    
    @Transactional
    public void updateSocialMedia(String newValue){

        Users uLoggedIn = LoginBean.getUserLoggedIn();
        uLoggedIn.setSocialMedialink(newValue);
        em.merge(uLoggedIn);
 
    }
    
    @Transactional
    public  String updateFirstName(String newValue){
        
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        uLoggedIn.setFirstName(newValue);
        em.merge(uLoggedIn);
        
        return "/faces/views/MainMenu/userHomePage.xhtml?faces-redirect=true";
    }
 
    @Transactional
    public void updatePersonnalDescription(String newValue){
        
        Users uLoggedIn = LoginBean.getUserLoggedIn();
        uLoggedIn.setPersonalDescription(newValue);
        em.merge(uLoggedIn);
        
    }

    
}
