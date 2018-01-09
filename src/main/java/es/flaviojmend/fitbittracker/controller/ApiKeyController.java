package es.flaviojmend.fitbittracker.controller;

import es.flaviojmend.fitbittracker.persistence.entity.ApiKey;
import es.flaviojmend.fitbittracker.persistence.entity.Weather;
import es.flaviojmend.fitbittracker.service.ApiKeyService;
import es.flaviojmend.fitbittracker.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiKey")
public class ApiKeyController {

    @Autowired
    private ApiKeyService apiKeyService;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<?> getApiKeys(){
        return new ResponseEntity<>(apiKeyService.listApiKeys(), HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<?> addApiKeys(@RequestBody String apiKeys){
        apiKeyService.addApiKeys(apiKeys);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
