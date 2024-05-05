package server.rest;

import io.netty.handler.codec.http2.Http2Exception;
import io.netty.handler.timeout.TimeoutException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;
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
    String LIST_DOMAINS_PATH = "/listDomains";

    String EMAIL = "email";
    String PASSWORD = "password";

    int MAX_RETRIES = 4;
    int MIN_DELAY = 500;
    int MAX_DELAY = 10000;


    /**
     * Create an admin account for a new domain
     * @param newDomain - new domain to be created
     * @return 200 - new domain with created admin user
     *         409 - domain already registered by other admin
     */
    @POST
    @Path(ADD_ADMIN_PATH)
    @Retry(retryOn = TimeoutException.class, abortOn = Http2Exception.class,
            maxRetries = MAX_RETRIES, delay = MIN_DELAY, maxDuration = MAX_DELAY)
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
    @Retry(retryOn = TimeoutException.class, abortOn = Http2Exception.class,
            maxRetries = MAX_RETRIES, delay = MIN_DELAY, maxDuration = MAX_DELAY)
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
    @Retry(retryOn = TimeoutException.class, abortOn = Http2Exception.class,
            maxRetries = MAX_RETRIES, delay = MIN_DELAY, maxDuration = MAX_DELAY)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    Response addAlert(byte[] alert);


    /**
     * List all domains
     * @return 200 - list of domains
     */
    @GET
    @Path(LIST_DOMAINS_PATH)
    @Retry(retryOn = TimeoutException.class, abortOn = Http2Exception.class,
            maxRetries = MAX_RETRIES, delay = MIN_DELAY, maxDuration = MAX_DELAY)
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
    @Retry(retryOn = TimeoutException.class, abortOn = Http2Exception.class,
            maxRetries = MAX_RETRIES, delay = MIN_DELAY, maxDuration = MAX_DELAY)
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
    @Retry(retryOn = TimeoutException.class, abortOn = Http2Exception.class,
            maxRetries = MAX_RETRIES, delay = MIN_DELAY, maxDuration = MAX_DELAY)
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
    @Retry(retryOn = TimeoutException.class, abortOn = Http2Exception.class,
            maxRetries = MAX_RETRIES, delay = MIN_DELAY, maxDuration = MAX_DELAY)
    @Produces(MediaType.APPLICATION_JSON)
    Response getStorage(@PathParam(EMAIL) String email, @QueryParam(PASSWORD) String password);
}
