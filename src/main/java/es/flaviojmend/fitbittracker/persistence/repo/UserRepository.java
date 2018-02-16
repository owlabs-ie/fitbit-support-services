package es.flaviojmend.fitbittracker.persistence.repo;

import es.flaviojmend.fitbittracker.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    public List<User> findAllByApp(String app);

}
