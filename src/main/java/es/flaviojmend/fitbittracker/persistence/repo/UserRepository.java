package es.flaviojmend.fitbittracker.persistence.repo;

import es.flaviojmend.fitbittracker.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
