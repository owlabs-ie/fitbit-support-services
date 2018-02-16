package es.flaviojmend.fitbittracker.controller;

import es.flaviojmend.fitbittracker.persistence.entity.Weather;
import es.flaviojmend.fitbittracker.service.TrackerService;
import es.flaviojmend.fitbittracker.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private TrackerService trackerService;

    @RequestMapping(method= RequestMethod.GET, value = "/{service}/{latitude}/{longitude}")
    public ResponseEntity<?> getWeatherByLatLong(@PathVariable String service, @PathVariable String latitude, @PathVariable String longitude, @RequestParam("app") String app, @RequestParam(value = "uuid", required = false) String uuid){
        try {
            trackerService.saveOrUpdateUser(uuid != null ? uuid : "unkn0wn", app != null ? app : "unkn0wn");
            return new ResponseEntity<Weather>(weatherService.getWeather(service, latitude, longitude, app), HttpStatus.OK);
        } catch (ExecutionException e) {
            return new ResponseEntity<Weather>(HttpStatus.NOT_ACCEPTABLE);
        } catch (InterruptedException e) {
            return new ResponseEntity<Weather>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
