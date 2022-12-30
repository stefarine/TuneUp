/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hec.soar.tuneup.v3.service;

import hec.soar.tuneup.v1.models.Track;
import hec.soar.tuneup.v1.models.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author stephane
 */
@Stateless
@Path("hec.soar.tuneup.v1.models.track")
public class TrackFacadeREST extends AbstractFacade<Track> {

    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;

    public TrackFacadeREST() {
        super(Track.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Track entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Track entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Track find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Path("/allTracks")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Track> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Track> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("/removeFromPlaylist/{tId}/{uId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void removeFromShoppingCart(@PathParam("tId") String tId, @PathParam("uId") String uId) {
        Track t = find(tId);
        Users u = getEntityManager().find(Users.class, uId);
        t.getUsersCollection().remove(u);
        getEntityManager().merge(t);

    }
    @GET
    @Path("/addFromPlaylist/{tId}/{uId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addFromShoppingCart(@PathParam("tId") String tId, @PathParam("uId") String uId) {
        Track t = find(tId);
        Users u = getEntityManager().find(Users.class, uId);
        t.getUsersCollection().add(u);
        getEntityManager().merge(t);

    }
    
    
}
