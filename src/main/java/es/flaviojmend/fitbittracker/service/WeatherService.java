package es.flaviojmend.fitbittracker.service;

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

    @Cacheable(value = "weather", key = "{#latitude, #longitude}")
    public Weather getWeather(String latitude, String longitude) {
        return openWeatherConsumer.getWeatherByLatLong(latitude,longitude);
    }





}
