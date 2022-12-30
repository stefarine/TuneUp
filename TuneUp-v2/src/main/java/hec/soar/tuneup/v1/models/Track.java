package hec.soar.tuneup.v1.models;

public class Track {
    
    private String id;
    private String name;
    private String preview_url;
    private String image;
    
    
    // constructor
    public Track(String id, String name, String preview_url, String image){
    this.id = id;
    this.name = name;
    this.preview_url = preview_url;
    this.image = image;
    }
    
    // getters
    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPreview_url(){
        return preview_url;
    }
    
    public String getImage(){
        return image;
    }
    
    // setters
    public void setId(String id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPreview_url(String preview_url){
        this.preview_url = preview_url;
    }
    
    public void setImage(String image){
        this.image = image;
    }
}
