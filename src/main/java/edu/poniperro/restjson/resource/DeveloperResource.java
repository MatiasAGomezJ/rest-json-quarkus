package edu.poniperro.restjson.resource;

import edu.poniperro.restjson.repository.Developer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/dev")
public class DeveloperResource {

    @Inject
    EntityManager em;

//    public DeveloperResource() {
//        fruits.add(new Fruit("Apple", "Winter fruit"));
//        fruits.add(new Fruit("Pineapple", "Tropical fruit"));
//    }

    @GET
    @Path("{name}")
    public Developer list(@PathParam("name") String name) {
        return em.find(Developer.class, name);
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Developer dev) {
        em.persist(dev);
        return Response.created(URI.create("/dev" + dev.getName())).build();
    }

//    @DELETE
//    public void delete(Developer dev) {
//        em.remove(dev);
//    }
}