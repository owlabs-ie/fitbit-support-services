package es.flaviojmend.fitbittracker.persistence.repo;

import es.flaviojmend.fitbittracker.persistence.entity.ApiKey;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApiKeyRepository extends CrudRepository<ApiKey, String> {


    public List<ApiKey> findAllByService(ServiceType service);

}
