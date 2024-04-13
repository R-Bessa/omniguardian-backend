package server.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import server.data.User;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, String> {
}
