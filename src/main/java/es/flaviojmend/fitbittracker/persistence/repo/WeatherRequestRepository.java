package es.flaviojmend.fitbittracker.persistence.repo;

import es.flaviojmend.fitbittracker.persistence.entity.ApiKey;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.entity.WeatherRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeatherRequestRepository extends CrudRepository<WeatherRequest, String> {


    public List<WeatherRequest> findAllByService(ServiceType service);

}
