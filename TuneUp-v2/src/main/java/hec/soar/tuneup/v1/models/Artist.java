
package hec.soar.tuneup.v1.models;

public class Artist {
    
    private String id;
    private String name;
    private String image;
    private String[] genres;
    
    
    // constructor
    public Artist(String id, String name, String image, String[] genreList){
        this.id = id;
        this.name = name;
        this.image = image;
        this.genres = genreList;     
    }
    
    // getters
    public String getId(){
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getImage(){
        return image;
    }
    
    public String[] getGenres(){
        return genres;
    }
    
    
    // setters
    public void setId(String id){
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public void setGenres(String[] genres){
        this.genres = genres;
    }
    
}
