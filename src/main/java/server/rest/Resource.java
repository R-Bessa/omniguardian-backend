package server.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import server.data.Camera;
import server.data.Domain;
import server.data.User;
import server.repositories.AlertRepository;
import server.repositories.CameraRepository;
import server.repositories.DomainRepository;
import server.repositories.UserRepository;
import server.utils.TokenGenerator;

@ApplicationScoped
@Transactional
public class Resource implements Service {

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
    public Response listDomains() {
        return Response.ok(domainRepository.listAll()).build();
    }
}
