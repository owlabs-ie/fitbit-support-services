package es.flaviojmend.fitbittracker.service;

import es.flaviojmend.fitbittracker.consumer.DarkSkyConsumer;
import es.flaviojmend.fitbittracker.consumer.OpenWeatherConsumer;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.entity.Weather;

import es.flaviojmend.fitbittracker.persistence.entity.WeatherRequest;
import es.flaviojmend.fitbittracker.persistence.repo.WeatherRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);


    @Autowired
    private OpenWeatherConsumer openWeatherConsumer;

    @Autowired
    private DarkSkyConsumer darkSkyConsumer;

    @Autowired
    private WeatherRequestService weatherRequestService;

    @Cacheable(value = "weather", key = "{#service, #latitude, #longitude}")
    public Weather getWeather(String service, String latitude, String longitude, String app) throws ExecutionException, InterruptedException {
        weatherRequestService.saveRequest(service,latitude,longitude,app);
        logger.info("Retrieving " + service);
        switch(service) {
            case "OPENWEATHER":
                return openWeatherConsumer.getWeatherByLatLong(latitude,longitude);

            case "DARKSKY":
                return darkSkyConsumer.getWeatherByLatLong(latitude,longitude);

        }
        return  openWeatherConsumer.getWeatherByLatLong(latitude,longitude);
    }





}
