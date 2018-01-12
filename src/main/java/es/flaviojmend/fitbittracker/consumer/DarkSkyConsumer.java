package es.flaviojmend.fitbittracker.consumer;

import com.github.wnameless.json.flattener.JsonFlattener;
import es.flaviojmend.fitbittracker.persistence.entity.Location;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.entity.Weather;
import es.flaviojmend.fitbittracker.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.logging.Logger;

@Component
public class DarkSkyConsumer implements WeatherConsumer {

    @Autowired
    private ApiKeyService apiKeyService;

    @Autowired
    private LocationConsumer locationConsumer;

    private RestTemplate restTemplate = new RestTemplate();

    private static final String ENDPOINT = "https://api.darksky.net/forecast/:appid/:lat,:lon?exclude=minutely,hourly,alerts,flags";
    private Logger logger = Logger.getLogger(this.toString());


    @Override
    public Weather getWeatherByLatLong(String latitude, String longitude) {
        String url = ENDPOINT.replace(":lat", latitude).replace(":lon",longitude).replace(":appid", apiKeyService.getRandomKey(ServiceType.DARKSKY));

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

        Double tempC = ((Double.parseDouble(result.get("currently.temperature").toString()) - 32)*5)/9;
        Double apparentTempC = ((Double.parseDouble(result.get("currently.apparentTemperature").toString()) - 32)*5)/9;

        return new Weather()
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setLocation(locationConsumer.getWeatherByLatLong(latitude,longitude).getDescription())
                .setSunrise(result.get("daily.data[0].sunriseTime").toString())
                .setSunset(result.get("daily.data[0].sunsetTime").toString())
                .setTemperatureC(tempC.toString())
                .setApparentTemperatureC(apparentTempC.toString())
                .setTemperatureF(result.get("currently.temperature").toString())
                .setApparentTemperatureF(result.get("currently.apparentTemperature").toString());
    }

}
