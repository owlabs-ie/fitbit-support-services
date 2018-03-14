package es.flaviojmend.fitbittracker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import es.flaviojmend.fitbittracker.enums.Version;
import es.flaviojmend.fitbittracker.persistence.entity.Weather;
import es.flaviojmend.fitbittracker.service.TrackerService;
import es.flaviojmend.fitbittracker.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private TrackerService trackerService;

    @RequestMapping(method= RequestMethod.GET, value = "/{service}/{latitude}/{longitude}", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getWeatherByLatLong(@PathVariable String service, @PathVariable String latitude, @PathVariable String longitude, @RequestParam("app") String app, @RequestParam(value = "uuid", required = false) String uuid){
        try {
            trackerService.saveOrUpdateUser(uuid != null ? uuid : "unkn0wn", app != null ? app : "unkn0wn");
            return new ResponseEntity<>(weatherService.getWeather(service, latitude, longitude, app, null, Version.V1), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (InvocationTargetException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (IllegalAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (NoSuchMethodException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
