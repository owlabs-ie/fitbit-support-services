package es.flaviojmend.fitbittracker.consumer;

import com.github.wnameless.json.flattener.JsonFlattener;
import es.flaviojmend.fitbittracker.persistence.entity.Location;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.entity.Weather;
import es.flaviojmend.fitbittracker.service.ApiKeyService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class DarkSkyConsumer implements WeatherConsumer {

    @Autowired
    private ApiKeyService apiKeyService;

    @Autowired
    private LocationConsumer locationConsumer;

    private RestTemplate restTemplate = new RestTemplate();

    private static final String ENDPOINT = "https://api.darksky.net/forecast/{appid}/{latitude},{longitude}?exclude=minutely,hourly,alerts,flags";

    private Logger logger = Logger.getLogger(this.toString());

    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = null;
        SSLContext context = null;
        try {
            System.setProperty("https.protocols", "TLSv1");
            context = SSLContext.getInstance("TLSv1");
            context.init(null, null, null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .setSSLContext(context)
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate = new RestTemplate(factory);
        return restTemplate;
    }

    @Override
    public Weather getWeatherByLatLong(String latitude, String longitude) {

        try {
            ResponseEntity<String> responseEntity = getRestTemplate().getForEntity(ENDPOINT, String.class, apiKeyService.getRandomKey(ServiceType.DARKSKY), latitude, longitude);
            return handleWeatherResponse(latitude, longitude, responseEntity);
        } catch (HttpClientErrorException e) {
            logger.warning("Error retrieving Weather: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            return getWeatherByLatLong(latitude, longitude);
        }

    }

    private Weather handleWeatherResponse(String latitude, String longitude, ResponseEntity<String> responseEntity) {
        Map<String, Object> result = JsonFlattener.flattenAsMap(responseEntity.getBody());

        Double tempC = ((Double.parseDouble(result.get("currently.temperature").toString()) - 32) * 5) / 9;
        Double apparentTempC = ((Double.parseDouble(result.get("currently.apparentTemperature").toString()) - 32) * 5) / 9;

        return new Weather()
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setLocation(locationConsumer.getWeatherByLatLong(latitude, longitude).getDescription())
                .setSunrise(result.get("daily.data[0].sunriseTime").toString())
                .setSunset(result.get("daily.data[0].sunsetTime").toString())
                .setTemperatureC(tempC.toString())
                .setApparentTemperatureC(apparentTempC.toString())
                .setTemperatureF(result.get("currently.temperature").toString())
                .setApparentTemperatureF(result.get("currently.apparentTemperature").toString())
                .setHumidity(result.get("currently.humidity").toString())
                .setPrecipitation(result.get("currently.precipProbability").toString());
    }

}
