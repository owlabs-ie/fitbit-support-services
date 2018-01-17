package es.flaviojmend.fitbittracker.consumer;

import com.github.wnameless.json.flattener.JsonFlattener;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.entity.Weather;
import es.flaviojmend.fitbittracker.service.ApiKeyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class OpenWeatherConsumer implements WeatherConsumer {

    @Autowired
    private ApiKeyService apiKeyService;

    private RestTemplate restTemplate = new RestTemplate();

    private Logger logger = Logger.getLogger(this.toString());

    private static final String ENDPOINT = "http://api.openweathermap.org/data/2.5/weather?units=imperial&lat=:lat&lon=:lon&appid=:appid";

    public Weather getWeatherByLatLong(String latitude, String longitude) {

        String url = ENDPOINT.replace(":lat", latitude).replace(":lon",longitude).replace(":appid", apiKeyService.getRandomKey(ServiceType.OPENWEATHER));

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            return handleWeatherResponse(latitude, longitude, responseEntity);
        } catch(HttpClientErrorException e) {
            logger.warning("Error retrieving Weather: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return getWeatherByLatLong(latitude, longitude);
        }

    }

    private Weather handleWeatherResponse(String latitude, String longitude, ResponseEntity<String> responseEntity) {
        Map<String, Object> result = JsonFlattener.flattenAsMap(responseEntity.getBody());

        Double tempC = ((Double.parseDouble(result.get("main.temp").toString()) - 32) * 5) / 9;

        return new Weather()
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setLocation(result.get("name").toString())
                .setSunrise(result.get("sys.sunrise").toString())
                .setSunset(result.get("sys.sunset").toString())
                .setTemperatureC(tempC.toString())
                .setTemperatureF(result.get("main.temp").toString())
                .setHumidity(result.get("main.humidity").toString());
    }

}
