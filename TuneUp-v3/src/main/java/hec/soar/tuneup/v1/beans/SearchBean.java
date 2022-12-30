package hec.soar.tuneup.v1.beans;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "searchBean")
@SessionScoped
public class SearchBean implements Serializable {

    
    private String searchByChoice; 
    private String trackToSearch;
    

    public void setOption(String option) {
        this.searchByChoice = option;
    }
    
    public String getOption() {
        return searchByChoice;
    }
    
    public void setSearch(String search) {
        this.trackToSearch = search;
    }
    
    public String getSearch() {
        return trackToSearch;
    }
    
    public String makeSearch(String searchByChoice, String searchString){
        // Si Aucun Search By n'est selectionné renvoi un msg d'erreur sur la page de search
        switch (searchByChoice) {
            case "artist":
                this.trackToSearch = searchString;
                return "/views/trackSearch/artistPage.xhtml?faces-redirect=true&search="+searchString;
                // j'utilise plus le nom dans le lien.
            case "track":
                this.trackToSearch = searchString;
                return "/views/trackSearch/trackPage.xhtml?faces-redirect=true&search="+searchString;
            default:
                return "/views/trackSearch/searchNewTracks_errorMode.xhtml?faces-redirect=true";
        }
    }
    
}