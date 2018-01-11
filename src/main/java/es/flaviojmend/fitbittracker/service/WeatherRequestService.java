package es.flaviojmend.fitbittracker.service;

import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.entity.WeatherRequest;
import es.flaviojmend.fitbittracker.persistence.entity.WeatherRequestStats;
import es.flaviojmend.fitbittracker.persistence.repo.WeatherRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeatherRequestService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherRequestService.class);

    @Autowired
    private WeatherRequestRepository weatherRequestRepository;

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


}
