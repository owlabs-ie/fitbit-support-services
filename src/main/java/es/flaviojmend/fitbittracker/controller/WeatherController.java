package es.flaviojmend.fitbittracker.controller;

import es.flaviojmend.fitbittracker.persistence.entity.Weather;
import es.flaviojmend.fitbittracker.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping(method= RequestMethod.GET, value = "/{service}/{latitude}/{longitude}")
    public ResponseEntity<?> getWeatherByLatLong(@PathVariable String service, @PathVariable String latitude, @PathVariable String longitude){
        try {
            return new ResponseEntity<Weather>(weatherService.getWeather(service, latitude, longitude), HttpStatus.OK);
        } catch (ExecutionException e) {
            return new ResponseEntity<Weather>(HttpStatus.NOT_ACCEPTABLE);
        } catch (InterruptedException e) {
            return new ResponseEntity<Weather>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
