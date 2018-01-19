package es.flaviojmend.fitbittracker.service;

import es.flaviojmend.fitbittracker.consumer.LocationConsumer;
import es.flaviojmend.fitbittracker.persistence.entity.Location;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.entity.WeatherRequest;
import es.flaviojmend.fitbittracker.persistence.entity.WeatherRequestStats;
import es.flaviojmend.fitbittracker.persistence.repo.WeatherRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WeatherRequestService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherRequestService.class);

    @Autowired
    private WeatherRequestRepository weatherRequestRepository;

    @Autowired
    private LocationConsumer locationConsumer;

    public void saveRequest(String service, String latitude, String longitude, String app) {
        WeatherRequest weatherRequest = new WeatherRequest()
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setRequestDate(new Date())
                .setService(ServiceType.valueOf(service))
                .setApp(app != null && !app.equals("") ? app : "UNKNOWN");

        weatherRequestRepository.save(weatherRequest);
        logger.info("Saved weather request for service " + service);
    }


    public WeatherRequestStats getWeatherRequestsStats(String app) {
        List<WeatherRequest> weatherRequests = weatherRequestRepository.findAllByApp(app);
        return new WeatherRequestStats().setCount(weatherRequests.size())
                .setRequestsList(weatherRequests);

    }


    public Map<String, Integer> getLocationWeatherRequestsStats(String app) {
        List<WeatherRequest> weatherRequests = weatherRequestRepository.findAllByApp(app);

        Map<String, Integer> locations = new TreeMap<>();

        weatherRequests.forEach(wr -> {
            Location location = locationConsumer.getWeatherByLatLong(wr.getLatitude(), wr.getLongitude());
            if(locations.get(location.getDescription()) != null) {
                locations.put(location.getDescription(), locations.get(location.getDescription()) +1);
            }
        });

        return locations;
    }
}
