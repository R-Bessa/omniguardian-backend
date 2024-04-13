package server.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import server.data.Domain;

@ApplicationScoped
public class DomainRepository implements PanacheRepositoryBase<Domain, String> {
}
