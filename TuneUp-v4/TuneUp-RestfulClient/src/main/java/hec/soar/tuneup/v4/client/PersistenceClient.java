package hec.soar.tuneup.v4.client;

import hec.soar.tuneup.v1.beans.LoginBean;
import hec.soar.tuneup.v1.exceptions.DoesNotExistException;
import hec.soar.tuneup.v1.models.Track;
import hec.soar.tuneup.v1.models.Users;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class PersistenceClient {
    
    private static final String USERS_URL = "http://localhost:8080/hec.soar_TuneUp-RestfulService_war_1.0-SNAPSHOT/resources/hec.soar.tuneup.v1.models.users";
    private static final String TRACKS_URL = "http://localhost:8080/hec.soar_TuneUp-RestfulService_war_1.0-SNAPSHOT/resources/hec.soar.tuneup.v1.models.track";
    private static final String ARTISTS_URL = "http://localhost:8080/hec.soar_TuneUp-RestfulService_war_1.0-SNAPSHOT/resources/hec.soar.tuneup.v1.models.artist";
    
    private static Client client;
    private static WebTarget target;
    private static PersistenceClient instance;

    private PersistenceClient() {
        PersistenceClient.client = ClientBuilder.newClient();
    }

    public static PersistenceClient getInstance() {
        if (instance == null) {
            instance = new PersistenceClient();
        }
        return instance;
    }
    
    public void createUser(Users user) {
        client.target(USERS_URL + "/create").request().post(Entity.entity(user, "application/xml"));
    }
    
    public Users checkPassword(String email, String password) throws DoesNotExistException {
        Users u = getUsersByEmail(email);
        if (u.getEmail().equals(email) & u.getPassword().equals(password)) {
            
            return u;
        }
        throw new DoesNotExistException("Users " + email + " does not exist.");
    }
    
    public Users getUsersByEmail(String email) {
        Users u = parseUser(client.target(USERS_URL + "/findByEmail/" + email).request().get(String.class));
        return u;
    }
    
    public void updateUser(Users user) {
        client.target(USERS_URL + "/edit/" + user.getEmail()).request().put(Entity.entity(user, "application/xml"));
    }
    
    public List<Track> getAllTracks() {
        List<Track> trackList = parseTrackList(client.target(TRACKS_URL + "/allTracks").request().get(String.class));
        //trackList.forEach(e -> {System.out.println("##########"+e.getName());});
        
        return trackList;
    }
    public void removeTrackFromPlaylist(Track t, Users u) {
        client.target(TRACKS_URL + "/removeFromPlaylist/" + t.getIdTrack() + "/" + u.getEmail()).request().get();
    }
    public void addTrackFromPlaylist(Track t, Users u) {
        client.target(TRACKS_URL + "/addFromPlaylist/" + t.getIdTrack() + "/" + u.getEmail()).request().get();
    }
    
    public void removeUserFromMatchesList(Users u) {
        System.out.println(">>> "+u.getEmail() +" AND THEN >> "+LoginBean.getUserLoggedIn().getEmail());
        client.target(USERS_URL + "/removeFromMatchList/"+ u.getEmail()+"/"+LoginBean.getUserLoggedIn().getEmail()).request().get();
    }
    
    public void addUserFromMatchesList(Users u) {
        System.out.println(">>> "+u.getEmail() +" AND THEN >> "+LoginBean.getUserLoggedIn().getEmail());
        client.target(USERS_URL + "/addFromMatchList/"+ u.getEmail()+"/"+LoginBean.getUserLoggedIn().getEmail()).request().get();
    }
    
    
    public List<Track> getTracksByTrackName(Track t) {
        List<Track> trackList = parseTrackList(client.target(TRACKS_URL + t.getIdTrack()).request().get(String.class));
        //trackList.forEach(e -> {System.out.println("##########"+e.getName());});
        
        return trackList;
    }
    
    public List<Users> getAllUsers() {
        List<Users> usersList = parseUsersList(client.target(USERS_URL).request().get(String.class));
        //trackList.forEach(e -> {System.out.println("##########"+e.getName());});
        
        return usersList;
    }
    
    private Users parseUser(String xml) {
        if (xml.length() == 0) {
            return null;
        }
        System.out.println(xml);
        Element e = (Element) parseDocument(xml).getElementsByTagName("users").item(0);

        Users user = new Users();
        user.setEmail(e.getElementsByTagName("email").item(0).getTextContent());
        user.setSocialMedialink(e.getElementsByTagName("socialMedialink").item(0).getTextContent());
        user.setFirstName(e.getElementsByTagName("firstName").item(0).getTextContent());
        user.setPassword(e.getElementsByTagName("password").item(0).getTextContent());
        user.setPersonalDescription(e.getElementsByTagName("personalDescription").item(0).getTextContent());
        user.setPicture(e.getElementsByTagName("picture").item(0).getTextContent());
        
        String dob = e.getElementsByTagName("dateOfBirth").item(0).getTextContent();
        int year = Integer.valueOf(dob.substring(0,4));
        int month = Integer.valueOf(dob.substring(5,7));
        int day = Integer.valueOf(dob.substring(8,10));
        
        Date date = new GregorianCalendar(year,month-1,day).getTime();
        user.setDateOfBirth(date);
        

        return user;
    }
    
    private List<Users> parseUsersList(String xml) {
        List<Users> UsersList = new ArrayList<>();
        NodeList list = parseDocument(xml).getElementsByTagName("users");
        for (int i = 0; i< list.getLength(); i++){
            Element e = (Element) list.item(i);
            
            Users user = new Users();
            user.setEmail(e.getElementsByTagName("email").item(0).getTextContent());
            user.setSocialMedialink(e.getElementsByTagName("socialMedialink").item(0).getTextContent());
            user.setFirstName(e.getElementsByTagName("firstName").item(0).getTextContent());
            user.setPassword(e.getElementsByTagName("password").item(0).getTextContent());
            user.setPersonalDescription(e.getElementsByTagName("personalDescription").item(0).getTextContent());
            user.setPicture(e.getElementsByTagName("picture").item(0).getTextContent());
        
            String dob = e.getElementsByTagName("dateOfBirth").item(0).getTextContent();
            int year = Integer.valueOf(dob.substring(0,4));
            int month = Integer.valueOf(dob.substring(5,7));
            int day = Integer.valueOf(dob.substring(8,10));

            Date date = new GregorianCalendar(year,month-1,day).getTime();
            user.setDateOfBirth(date);

            //get userList Likes
            NodeList list2 = e.getElementsByTagName("usersCollection");
            List<Users> uPL = new ArrayList<>();
            
            if (list2.getLength()> 0){
                for (int j = 0; j< list2.getLength(); j++){
                    Element el = (Element) list2.item(j);
                    Users user2 = new Users();
                    user2.setEmail(el.getElementsByTagName("email").item(0).getTextContent());
                    user2.setSocialMedialink(el.getElementsByTagName("socialMedialink").item(0).getTextContent());
                    user2.setFirstName(el.getElementsByTagName("firstName").item(0).getTextContent());
                    user2.setPassword(el.getElementsByTagName("password").item(0).getTextContent());
                    user2.setPersonalDescription(el.getElementsByTagName("personalDescription").item(0).getTextContent());
                    user2.setPicture(el.getElementsByTagName("picture").item(0).getTextContent());

                    String dob2 = el.getElementsByTagName("dateOfBirth").item(0).getTextContent();
                    int year2 = Integer.valueOf(dob2.substring(0,4));
                    int month2 = Integer.valueOf(dob2.substring(5,7));
                    int day2 = Integer.valueOf(dob2.substring(8,10));

                    Date date2 = new GregorianCalendar(year2,month2-1,day2).getTime();
                    user.setDateOfBirth(date2);
                    uPL.add(user2);
                } 
            }
            user.setUsersCollection(uPL);
            UsersList.add(user);
        }
        return UsersList;
    }
    
    
    
    private List<Track> parseTrackList(String xml) {
        List<Track> trackList = new ArrayList<>();
        NodeList list = parseDocument(xml).getElementsByTagName("track");
        for (int i = 0; i< list.getLength(); i++){
            Element e = (Element) list.item(i);
            
            Track track = new Track();
            
            track.setIdTrack((e.getElementsByTagName("idTrack").item(0).getTextContent()));
            track.setName((e.getElementsByTagName("name").item(0).getTextContent()));
            track.setPicture((e.getElementsByTagName("picture").item(0).getTextContent()));
            track.setPreview((e.getElementsByTagName("preview").item(0).getTextContent()));
            
            //get playlist
            NodeList list2 = e.getElementsByTagName("usersCollection");
            List<Users> uPL = new ArrayList<>();
            
            if (list2.getLength()> 0){
                for (int j = 0; j< list2.getLength(); j++){
                    Element el = (Element) list2.item(j);

                    Users user = new Users();
                    user.setEmail(el.getElementsByTagName("email").item(0).getTextContent());
                    user.setSocialMedialink(el.getElementsByTagName("socialMedialink").item(0).getTextContent());
                    user.setFirstName(el.getElementsByTagName("firstName").item(0).getTextContent());
                    user.setPassword(el.getElementsByTagName("password").item(0).getTextContent());
                    user.setPersonalDescription(el.getElementsByTagName("personalDescription").item(0).getTextContent());
                    user.setPicture(el.getElementsByTagName("picture").item(0).getTextContent());

                    String dob = el.getElementsByTagName("dateOfBirth").item(0).getTextContent();
                    int year = Integer.valueOf(dob.substring(0,4));
                    int month = Integer.valueOf(dob.substring(5,7));
                    int day = Integer.valueOf(dob.substring(8,10));

                    Date date = new GregorianCalendar(year,month-1,day).getTime();
                    user.setDateOfBirth(date);

                    uPL.add(user); 
                }
            }
            
            
            track.setUsersCollection(uPL);
            trackList.add(track);
        }
        
        return trackList;
    }
    
    
    
    private Document parseDocument(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
