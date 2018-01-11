package es.flaviojmend.fitbittracker.controller;

import es.flaviojmend.fitbittracker.persistence.entity.Weather;
import es.flaviojmend.fitbittracker.persistence.entity.WeatherRequest;
import es.flaviojmend.fitbittracker.persistence.entity.WeatherRequestStats;
import es.flaviojmend.fitbittracker.service.WeatherRequestService;
import es.flaviojmend.fitbittracker.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/stats")
public class WeatherRequestController {

    @Autowired
    private WeatherRequestService weatherRequestServiceService;

    @RequestMapping(method= RequestMethod.GET, value = "/{app}")
    public ResponseEntity<?> getAppRequests(@PathVariable String app){
        return new ResponseEntity<WeatherRequestStats>(weatherRequestServiceService.getWeatherRequestsStats(app), HttpStatus.OK);

    }



}
