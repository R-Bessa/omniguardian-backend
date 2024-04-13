package server.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import server.data.Camera;

@ApplicationScoped
public class CameraRepository implements PanacheRepositoryBase<Camera, String> {
}
