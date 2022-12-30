package hec.soar.tuneup.v1.database;

import hec.soar.tuneup.v1.models.User;
import hec.soar.tuneup.v1.models.Artist;
import hec.soar.tuneup.v1.models.Track;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MockDatabase {
    private static MockDatabase instance;
    
    // tables
    private Map<String, User> users;
    private Map<String, Track> tracks;
    private Map<String, Artist> artists;
    
    private Map<Artist, ArrayList<Track>> trackByArtist;
    private Map<User, ArrayList<Track>> inThePlaylist;
    private Map<User, ArrayList<User>> likeUser;
    
    public MockDatabase(){
        
    
    // TABLE USER
    users = new HashMap();
    users.put("user1@unil.ch", new User("user1@unil.ch","www.instagram.com/user1","Pedro",
            LocalDate.of(1996,12,6),"myPassword","i am user1. I like cheese. My favorite dish is cheese fondue. My favorite cheese is Gruyere. My favorite animal is cheese."
            ,"https://i.ibb.co/Kmgwh82/pexels-elle-hughes-1680172.jpg"));
    users.put("user2@unil.ch", new User("user2@unil.ch","www.instagram.com/user2","Jerome",
            LocalDate.of(1983,11,26),"myPassword123","i am user2 and i like cheese"
            ,"https://i.ibb.co/Kmgwh82/pexels-elle-hughes-1680172.jpg"));
    users.put("user3@unil.ch", new User("user3@unil.ch","www.instagram.com/user3","Andrea",
            LocalDate.of(1992,12,6),"myPasswordblabla","I like cats. I hate dogs. I wich i could be a cat in a cat’s world."
            ,"https://i.ibb.co/Y8jJM1Z/pexels-andrea-piacquadio-774866.jpg"));
    users.put("user4@unil.ch", new User("user4@unil.ch","www.instagram.com/user4","Barnabé",
            LocalDate.of(2001,1,4),"myPass","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            ,"https://i.ibb.co/VCKhMxD/pexels-justin-shaifer-1222271-1.jpg"));   
        
    
    // TABLE ARTIST
    artists = new HashMap();
    artists.put("idArtist001", new Artist("idArtist001","Nirvana",
            "https://extrachill.com/wp-content/uploads/2021/06/nirvana-logo.jpeg",
            new String[]{"Grunge","Alternative rock"}));
    artists.put("idArtist002",new Artist("idArtist002","Michael Jackson",
            "https://media.vogue.fr/photos/5f23e65ba806c0ec57bc13e6/4:3/w_755,h_566,c_limit/thriller-758x758.jpg",
            new String[]{"Pop","Soul","Funk","Rock","Disco"}));
    artists.put("idArtist003",new Artist("idArtist003","Nekfeu",
            "https://www.booska-p.com/wp-content/uploads/2022/06/Nekfeu-Studio-Visu-News-1024x750.jpg",
            new String[]{"Rap","French Rap"}));
    
    // TABLE TRACK
    tracks = new HashMap();
    tracks.put("idTrack001", new Track("idTrack001","Come as You Are",
            "https://p.scdn.co/mp3-preview/03dd99f372db73d05e7f8726a1fee792b5b4e369?cid=162b7dc01f3a4a2ca32ed3cec83d1e02",
            "https://extrachill.com/wp-content/uploads/2021/06/nirvana-logo.jpeg"));
    tracks.put("idTrack002", new Track("idTrack002","Smells like Teen Spirit",
            "https://p.scdn.co/mp3-preview/9781673a70278effbced98841341a2321e846a62?cid=162b7dc01f3a4a2ca32ed3cec83d1e02",
            "https://extrachill.com/wp-content/uploads/2021/06/nirvana-logo.jpeg"));
    tracks.put("idTrack003", new Track("idTrack003","Billie Jean",
            "https://p.scdn.co/mp3-preview/3c0a3971d37a42917fc3e28c04bcc3e8be6379c4?cid=162b7dc01f3a4a2ca32ed3cec83d1e02",
            "https://media.vogue.fr/photos/5f23e65ba806c0ec57bc13e6/4:3/w_755,h_566,c_limit/thriller-758x758.jpg"));
    tracks.put("idTrack004", new Track("idTrack004","Beat It",
            "https://p.scdn.co/mp3-preview/838cb10d23856033fd7aea0d223f687d69f30fa4?cid=162b7dc01f3a4a2ca32ed3cec83d1e02",
            "https://media.vogue.fr/photos/5f23e65ba806c0ec57bc13e6/4:3/w_755,h_566,c_limit/thriller-758x758.jpg"));
    tracks.put("idTrack005", new Track("idTrack005","On Verra",
            "https://p.scdn.co/mp3-preview/54d4fce4cccde23d4af6f952df0595e453f7c4d5?cid=162b7dc01f3a4a2ca32ed3cec83d1e02",
            "https://www.booska-p.com/wp-content/uploads/2022/06/Nekfeu-Studio-Visu-News-1024x750.jpg"));
    tracks.put("idTrack006", new Track("idTrack006","Nique les clones",
            "https://p.scdn.co/mp3-preview/fb6459659a6794f30ba0c2649e9e9e111ee7f887?cid=162b7dc01f3a4a2ca32ed3cec83d1e02",
            "https://www.booska-p.com/wp-content/uploads/2022/06/Nekfeu-Studio-Visu-News-1024x750.jpg"));
    
    
    // TABLE  TrackByArtist
    trackByArtist = new HashMap();
    trackByArtist.put(artists.get("idArtist001"),new ArrayList<>());
    trackByArtist.get(artists.get("idArtist001")).add(tracks.get("idTrack001"));
    trackByArtist.get(artists.get("idArtist001")).add(tracks.get("idTrack002"));
    
    trackByArtist.put(artists.get("idArtist002"),new ArrayList<>());
    trackByArtist.get(artists.get("idArtist002")).add(tracks.get("idTrack003"));
    trackByArtist.get(artists.get("idArtist002")).add(tracks.get("idTrack004"));
    
    trackByArtist.put(artists.get("idArtist003"),new ArrayList<>());
    trackByArtist.get(artists.get("idArtist003")).add(tracks.get("idTrack005"));
    trackByArtist.get(artists.get("idArtist003")).add(tracks.get("idTrack006"));
    
    // Table inThePlaylist
    inThePlaylist = new HashMap();
    inThePlaylist.put(users.get("user1@unil.ch"),new ArrayList<>());
    inThePlaylist.get(users.get("user1@unil.ch")).add(tracks.get("idTrack001"));
    inThePlaylist.get(users.get("user1@unil.ch")).add(tracks.get("idTrack006"));
    inThePlaylist.get(users.get("user1@unil.ch")).add(tracks.get("idTrack004"));
    
    inThePlaylist.put(users.get("user2@unil.ch"),new ArrayList<>());
    inThePlaylist.get(users.get("user2@unil.ch")).add(tracks.get("idTrack001"));
    inThePlaylist.get(users.get("user2@unil.ch")).add(tracks.get("idTrack002"));
    inThePlaylist.get(users.get("user2@unil.ch")).add(tracks.get("idTrack006"));
    
    inThePlaylist.put(users.get("user3@unil.ch"),new ArrayList<>());
    inThePlaylist.get(users.get("user3@unil.ch")).add(tracks.get("idTrack004"));
    inThePlaylist.get(users.get("user3@unil.ch")).add(tracks.get("idTrack006"));
    inThePlaylist.get(users.get("user3@unil.ch")).add(tracks.get("idTrack001"));
    
    inThePlaylist.put(users.get("user4@unil.ch"),new ArrayList<>());
    inThePlaylist.get(users.get("user4@unil.ch")).add(tracks.get("idTrack005"));
    inThePlaylist.get(users.get("user4@unil.ch")).add(tracks.get("idTrack006"));
    inThePlaylist.get(users.get("user4@unil.ch")).add(tracks.get("idTrack004"));
    inThePlaylist.get(users.get("user4@unil.ch")).add(tracks.get("idTrack001"));
    inThePlaylist.get(users.get("user4@unil.ch")).add(tracks.get("idTrack002"));
    
    
    // TABLE likeUser
    likeUser = new HashMap();
     likeUser.put(users.get("user1@unil.ch"),new ArrayList<>());
     likeUser.get(users.get("user1@unil.ch")).add(users.get("user4@unil.ch"));
     likeUser.get(users.get("user1@unil.ch")).add(users.get("user3@unil.ch"));
     
     
     
     likeUser.put(users.get("user2@unil.ch"),new ArrayList<>());
     likeUser.get(users.get("user2@unil.ch")).add(users.get("user1@unil.ch"));
     likeUser.get(users.get("user2@unil.ch")).add(users.get("user3@unil.ch"));
     
     
     likeUser.put(users.get("user3@unil.ch"),new ArrayList<>());
     likeUser.get(users.get("user3@unil.ch")).add(users.get("user2@unil.ch"));
     likeUser.get(users.get("user3@unil.ch")).add(users.get("user1@unil.ch"));

     
     likeUser.put(users.get("user4@unil.ch"),new ArrayList<>());
     likeUser.get(users.get("user4@unil.ch")).add(users.get("user1@unil.ch"));

    }
    
    public static MockDatabase getInstance() {
        if (instance == null) {
            instance = new MockDatabase();
        }
        return instance;
    }
    
    // getters
    
    public Map<String, User> getUsers(){
        return users;
    }
    
    public Map<String, Track> getTracks(){
        return tracks;
    }
    
    public Map<String, Artist> getArtists(){
        return artists;
    }
    
    public Map<Artist, ArrayList<Track>> getTrackByArtist(){
        return trackByArtist;
    }
    
    public Map<User, ArrayList<Track>> getInThePlaylist(){
        return inThePlaylist;
    }
    
    public Map<User, ArrayList<User>> getLikeUser(){
        return likeUser;
    }

    
    
    // New User, artist or track
    public void addUser(User user) {
        users.put(user.getEmail(),user);
    }
    
    public void addTrack(Track track) {
        tracks.put(track.getId(),track);
    }
    
    public void addArtist(Artist artist) {
        artists.put(artist.getId(),artist);
    }
    
    public void createPlayList(User u){
        inThePlaylist.put(u, new ArrayList<>());
    }
    
    public void createLikeList(User u){
        likeUser.put(u, new ArrayList<>());
    }
    
    // add or remove a track from the playlist
    public void userLikeTrack(User user,Track track ){
        inThePlaylist.get(user).add(track);
    }
    
    public void userDeleteTrack(User user, Track track){
        inThePlaylist.get(user).remove(track);
    }
    
    // like or unlike a user
    public void userLikeUser(User mainUser, User likedUser){
        likeUser.get(mainUser).add(likedUser);
    }
    
    public void userDeleteLikedUser(User mainUser, User likedUser){
        likeUser.get(mainUser).remove(likedUser);
    }
    
    // Update User's Profile
    public void updateSocialMediaLink(User user, String socialMedia_link){
        user.setSocialMedia_link(socialMedia_link);
    }
    
    public void updateFirstName(User user, String first_name){
        user.setFirst_name(first_name);
    }
    
    public void updateDateOfBirth(User user, LocalDate date){
        user.setDate_of_birth(date);
    }
    
    public void updatePassword(User user, String password){
        user.setPassword(Integer.toString(password.hashCode()));
    }
    
    public void updateDescription(User user, String description){
        user.setPersonalDescription(description);
    }
    
    
}
