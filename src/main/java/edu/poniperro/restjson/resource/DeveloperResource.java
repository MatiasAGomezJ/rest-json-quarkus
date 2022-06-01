package edu.poniperro.restjson.resource;

import edu.poniperro.restjson.repository.Developer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/dev")
public class DeveloperResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Developer> getDevs() {
        return Developer.findAll().list();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Developer getDevByName(@NotNull @PathParam("name") String name) {
        return Developer.find("name", name).firstResult();
    }

    @GET
    @Path("{name}/{age}")
    @Produces(MediaType.APPLICATION_JSON)
    public Developer getDevByNameAge(@NotNull @PathParam("name") String name, @NotNull @PathParam("age") Integer age) {
        return Developer.find("name = ?1 and age = ?2", name, age).firstResult();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDev(Developer dev) {
        dev.persist();
        return Response.created(URI.create("/dev" + dev.id)).build();
    }

}