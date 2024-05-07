package server.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import server.data.*;
import server.repositories.AlertRepository;
import server.repositories.CameraRepository;
import server.repositories.DomainRepository;
import server.repositories.UserRepository;
import server.utils.ImageSerializer;
import server.utils.TokenGenerator;

import java.awt.image.BufferedImage;
import java.util.List;

@ApplicationScoped
@Transactional
public class DatabaseResource implements DatabaseService {

    @Inject
    UserRepository userRepository;

    @Inject
    DomainRepository domainRepository;

    @Inject
    CameraRepository cameraRepository;

    @Inject
    AlertRepository alertRepository;


    @Override
    public Response addAdmin(Domain newDomain) {
        String domainName = newDomain.getName();

        if(domainRepository.findById(domainName) != null)
            return Response.status(Response.Status.CONFLICT).build();

        for(Camera newCamera: newDomain.getCameras())
            cameraRepository.persist(newCamera);

        String token = TokenGenerator.generateAuthorizationToken();
        newDomain.setAuthorizationToken(token);
        domainRepository.persist(newDomain);

        User admin = newDomain.getUsers().get(0);
        admin.setAuthorizationToken(token);
        userRepository.persist(admin);

        return Response.ok(admin).build();
    }

    @Override
    public Response addGuest(User user) {
        Domain domain = domainRepository.findById(user.getDomain());
        if(domain == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        if(!domain.getGuestCode().equals(user.getGuestCode()))
            return Response.status(Response.Status.FORBIDDEN).build();

        user.setAlarmCode(domain.getAlarmCode());
        user.setAuthorizationToken(domain.getAuthorizationToken());
        userRepository.persist(user);

        return Response.ok(user).build();
    }

    @Override
    public Response addAlert(Alert alert) {
        /*Camera camera = cameraRepository.findById(alert.getCamera());
        if(camera == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        if(!camera.isOn())
            return Response.status(Response.Status.BAD_REQUEST).build();*/

        BufferedImage buf = ImageSerializer.deserializeImage(alert.getImageBytes());
        ImageSerializer.saveAsPNG(buf, "alert.png");
        System.out.println(alert.getTimestamp());
        //alertRepository.persist(alert);
        return Response.ok("Record created!").build();
    }


    @Override
    public Response listDomains() {
        return Response.ok(domainRepository.listAll()).build();
    }

    @Override
    public Response getUser(String email, String password) {
        User user = userRepository.findById(email);
        if(!password.equals(user.getPassword()))
            return Response.status(Response.Status.FORBIDDEN).build();

        return Response.ok(user).build();
    }

    @Override
    public Response getLastAlert(String email, String password) {
        User user = userRepository.findById(email);
        if(!password.equals(user.getPassword()))
            return Response.status(Response.Status.FORBIDDEN).build();

        Domain domain = domainRepository.findById(user.getDomain());
        List<Alert> alerts = domain.getAlerts();
        Alert lastAlert = null;
        int nAlerts = alerts.size();
        if(nAlerts != 0)
            lastAlert = alerts.get(nAlerts - 1);
        return Response.ok(lastAlert).build();
    }

    @Override
    public Response getStorage(String email, String password) {
        User user = userRepository.findById(email);
        if(!password.equals(user.getPassword()))
            return Response.status(Response.Status.FORBIDDEN).build();

        Domain domain = domainRepository.findById(user.getDomain());
        List<Alert> alerts = domain.getAlerts();
        Alert lastAlert = null;
        int nAlerts = alerts.size();
        if(nAlerts != 0)
            lastAlert = alerts.get(nAlerts - 1);
        Storage storage = new Storage(user, lastAlert);
        return Response.ok(storage).build();
    }

}
