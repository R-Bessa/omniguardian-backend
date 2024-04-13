package server.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import server.data.Alert;

@ApplicationScoped
public class AlertRepository implements PanacheRepositoryBase<Alert, String> {
}
