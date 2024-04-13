package server.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.hibernate.exception.ConstraintViolationException;
import server.data.Camera;
import server.data.Domain;
import server.data.User;
import server.repositories.AlertRepository;
import server.repositories.CameraRepository;
import server.repositories.DomainRepository;
import server.repositories.UserRepository;

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
    public Response addUser(User user) {
        if(user.isAdmin()) {
            Domain newDomain = user.getDomain();
            String domainName = newDomain.getName();

            if(domainRepository.findById(domainName) != null)
                return Response.status(Response.Status.CONFLICT).build();

            for(Camera newCamera: newDomain.getCameras())
                cameraRepository.persist(newCamera);

            domainRepository.persist(newDomain);
            user.setAuthorizationToken(newDomain.getAuthorizationToken());
            userRepository.persist(user);
        }

        else {
            Domain domain = domainRepository.findById(user.getDomain().getName());
            if(domain == null)
                return Response.status(Response.Status.NOT_FOUND).build();

            user.setAlarmCode(domain.getAlarmCode());
            user.setAuthorizationToken(domain.getAuthorizationToken());
            userRepository.persist(user);
        }

        return Response.ok(user).build();
    }





    @Override
    public Response listDomains() {
        return Response.ok(domainRepository.listAll()).build();
    }
}
