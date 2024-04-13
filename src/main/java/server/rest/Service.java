package server.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import server.data.User;


//TODO Retries

@Path("/omniguardian-server")
public interface Service {

    @Path("/addUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response addUser(User user);

    @Path("/listDomains")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response listDomains();
}
