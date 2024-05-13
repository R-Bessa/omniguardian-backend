package server.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import server.data.Alert;
import server.data.Domain;
import server.data.User;



@Path(DatabaseService.PATH)
public interface DatabaseService {
    String PATH = "/omniguardian-server";
    String ADD_ADMIN_PATH = "/addAdmin";
    String ADD_GUEST_PATH = "/addGuest";
    String ADD_ALERT_PATH = "/addAlert";
    String GET_USER_PATH = "/getUser";
    String GET_LAST_ALERT_PATH = "/getLastAlert";
    String GET_STORAGE_PATH = "/getStorage";
    String GET_DEFAULT_ALERT = "/getDefaultAlert";
    String LIST_DOMAINS_PATH = "/listDomains";
    String GET_ALERTS_PATH = "/getAlerts";
    String GET_POSITIVE_ALERTS_PATH = "/getPositiveAlerts";
    String GET_FALSE_ALERTS_PATH = "/getFalseAlerts";
    String GET_USER_VERIFICATION_PATH = "/getUserVerification";

    String EMAIL = "email";
    String PASSWORD = "password";
    String TOKEN = "token";


    /**
     * Create an admin account for a new domain
     * @param newDomain - new domain to be created
     * @return 200 - new domain with created admin user
     *         409 - domain already registered by other admin
     */
    @POST
    @Path(ADD_ADMIN_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response addAdmin(Domain newDomain);


    /**
     * Create a guest account in domain
     * @param user - new guest user
     * @return 200 - new guest user
     *         404 - domain do not exist
     *         403 - invalid guest code
     */
    @POST
    @Path(ADD_GUEST_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response addGuest(User user);


    /**
     * Create an alert record
     * @param alert - new alert
     * @return created alert
     */
    @POST
    @Path(ADD_ALERT_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    Response addAlert(Alert alert);


    /**
     * List all domains
     * @return 200 - list of domains
     */
    @GET
    @Path(LIST_DOMAINS_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    Response listDomains();


    /**
     * Get user data
     * @param email - user email
     * @param password - user password uid
     * @return 200 - user data
     *         403 - invalid user password uid
     */
    @GET
    @Path(GET_USER_PATH + "/{" + EMAIL + "}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUser(@PathParam(EMAIL) String email, @QueryParam(PASSWORD) String password);


    /**
     * Get last alert data
     * @param email - user email
     * @param password - user password uid
     * @return 200 - last alert data
     *         403 - invalid user password uid
     */
    @GET
    @Path(GET_LAST_ALERT_PATH + "/{" + EMAIL + "}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getLastAlert(@PathParam(EMAIL) String email, @QueryParam(PASSWORD) String password);


    /**
     * Get user storage
     * @param email - user email
     * @param password - user password uid
     * @return 200 - user stored data
     *         403 - invalid user password uid
     */
    @GET
    @Path(GET_STORAGE_PATH + "/{" + EMAIL + "}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getStorage(@PathParam(EMAIL) String email, @QueryParam(PASSWORD) String password);


    /**
     * Get default alert for debug purposes
     * @return default alert
     */
    @GET
    @Path(GET_DEFAULT_ALERT)
    @Produces(MediaType.APPLICATION_JSON)
    Response getDefaultAlert();


    /**
     * Get all alerts in domain
     * @return all alerts
     */
    @GET
    @Path(GET_ALERTS_PATH + "/{" + EMAIL + "}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getAlerts(@PathParam(EMAIL) String email, @QueryParam(TOKEN) String token);

    /**
     * Get positive alerts in domain
     * @return positive alerts
     */
    @GET
    @Path(GET_POSITIVE_ALERTS_PATH + "/{" + EMAIL + "}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getPositiveAlerts(@PathParam(EMAIL) String email, @QueryParam(TOKEN) String token);


    /**
     * Get false alerts in domain
     * @return false alerts
     */
    @GET
    @Path(GET_FALSE_ALERTS_PATH + "/{" + EMAIL + "}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getFalseAlerts(@PathParam(EMAIL) String email, @QueryParam(TOKEN) String token);


    @GET
    @Path(GET_USER_VERIFICATION_PATH + "/{" + EMAIL + "}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserVerification(@PathParam(EMAIL) String email, @QueryParam(PASSWORD) String password);
}
