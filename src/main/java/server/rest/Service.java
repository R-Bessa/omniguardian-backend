package server.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import server.data.Domain;
import server.data.User;



@Path(Service.PATH)
public interface Service {
    String PATH = "/omniguardian-server";
    String ADD_ADMIN_PATH = "/addAdmin";
    String ADD_GUEST_PATH = "/addGuest";
    String GET_USER_PATH = "/getUser";
    String GET_LAST_ALERT_PATH = "/getLastAlert";
    String GET_STORAGE_PATH = "/getStorage";
    String LIST_DOMAINS_PATH = "/listDomains";

    String EMAIL = "email";
    String PASSWORD = "password";



    //TODO Retries


    @Path(ADD_ADMIN_PATH)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response addAdmin(Domain newDomain);

    @Path(ADD_GUEST_PATH)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response addGuest(User user);

    @Path(LIST_DOMAINS_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response listDomains();

    @Path(GET_USER_PATH + "/{" + EMAIL + "}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getUser(@PathParam(EMAIL) String email, @QueryParam(PASSWORD) String password);

    @Path(GET_LAST_ALERT_PATH + "/{" + EMAIL + "}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getLastAlert(@PathParam(EMAIL) String email, @QueryParam(PASSWORD) String password);

    @Path(GET_STORAGE_PATH + "/{" + EMAIL + "}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getStorage(@PathParam(EMAIL) String email, @QueryParam(PASSWORD) String password);
}
