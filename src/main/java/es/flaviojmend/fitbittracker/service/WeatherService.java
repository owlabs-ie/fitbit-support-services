package es.flaviojmend.fitbittracker.service;

import es.flaviojmend.fitbittracker.consumer.DarkSkyConsumer;
import es.flaviojmend.fitbittracker.consumer.OpenWeatherConsumer;
import es.flaviojmend.fitbittracker.persistence.entity.Weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {

    @Autowired
    private OpenWeatherConsumer openWeatherConsumer;

    @Autowired
    private DarkSkyConsumer darkSkyConsumer;

    @Cacheable(value = "weather", key = "{#service, #latitude, #longitude}")
    public Weather getWeather(String service, String latitude, String longitude) {
        switch(service) {
            case "owm":
                return openWeatherConsumer.getWeatherByLatLong(latitude,longitude);

            case "drk":
                return darkSkyConsumer.getWeatherByLatLong(latitude,longitude);

        }
        return  openWeatherConsumer.getWeatherByLatLong(latitude,longitude);
    }





}
