package es.flaviojmend.fitbittracker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import es.flaviojmend.fitbittracker.consumer.DarkSkyConsumer;
import es.flaviojmend.fitbittracker.consumer.OpenWeatherConsumer;
import es.flaviojmend.fitbittracker.persistence.entity.Weather;

import es.flaviojmend.fitbittracker.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);


    @Autowired
    private OpenWeatherConsumer openWeatherConsumer;

    @Autowired
    private DarkSkyConsumer darkSkyConsumer;

    @Autowired
    private WeatherRequestService weatherRequestService;

    @Cacheable(value = "weather", key = "{#service, #latitude, #longitude, #fields}")
    public String getWeather(String service, String latitude, String longitude, String app, String fields) throws IllegalAccessException, JsonProcessingException, NoSuchMethodException, InvocationTargetException {
        weatherRequestService.saveRequest(service,latitude,longitude,app);
        logger.info("Retrieving " + service);

        Weather weatherResponse;

        switch(service) {
            case "OPENWEATHER":
                weatherResponse = openWeatherConsumer.getWeatherByLatLong(latitude,longitude);
                break;

            case "DARKSKY":
                weatherResponse = darkSkyConsumer.getWeatherByLatLong(latitude,longitude);
                break;

            default:
                weatherResponse = openWeatherConsumer.getWeatherByLatLong(latitude,longitude);
                break;

        }


        return JSONUtils.retrieveFieldsFromWeatherObject(fields, weatherResponse);
    }





}
