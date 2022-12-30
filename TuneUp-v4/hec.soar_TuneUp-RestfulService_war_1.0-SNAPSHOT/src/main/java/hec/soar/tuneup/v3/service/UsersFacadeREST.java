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
@Path("hec.soar.tuneup.v1.models.users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "soar_PU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Path("/create")
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
        super.create(entity);
    }
    
    @GET
    @Path("/findByEmail/{email}")
    public Users findByName(@PathParam("email") String email) {
        return super.findByEmail("Users.findByEmail", "email", email);
    }

    @PUT
    @Path("edit/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Users entity) {
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
    public Users find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("/removeFromMatchList/{uId}/{uLoggedId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void removeFromShoppingCart(@PathParam("uId") String uId, @PathParam("uLoggedId") String uLoggedId) {
        Users u = getEntityManager().find(Users.class, uId);
        Users uLoggedIn = getEntityManager().find(Users.class, uLoggedId);
        u.getUsersCollection().remove(uLoggedIn);
        System.out.println("u> "+u.getEmail()+" AND UL > "+ uLoggedIn.getEmail() );
        getEntityManager().merge(u);

    }
    
    @GET
    @Path("/addFromMatchList/{uId}/{uLoggedId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void addFromShoppingCart(@PathParam("uId") String uId, @PathParam("uLoggedId") String uLoggedId) {
        Users u = getEntityManager().find(Users.class, uId);
        Users uLoggedIn = getEntityManager().find(Users.class, uLoggedId);
        u.getUsersCollection().add(uLoggedIn);
        System.out.println("u> "+u.getEmail()+" AND UL > "+ uLoggedIn.getEmail() );
        getEntityManager().merge(u);

    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
