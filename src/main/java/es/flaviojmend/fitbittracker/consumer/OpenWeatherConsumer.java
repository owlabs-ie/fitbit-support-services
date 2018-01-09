package es.flaviojmend.fitbittracker.consumer;

import com.github.wnameless.json.flattener.JsonFlattener;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.entity.Weather;
import es.flaviojmend.fitbittracker.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class OpenWeatherConsumer implements WeatherConsumer {

    @Autowired
    private ApiKeyService apiKeyService;

    private RestTemplate restTemplate = new RestTemplate();

    private static final String ENDPOINT = "http://api.openweathermap.org/data/2.5/weather?units=imperial&lat=:lat&lon=:lon&appid=:appid";

    public Weather getWeatherByLatLong(String latitude, String longitude) {

        String url = ENDPOINT.replace(":lat", latitude).replace(":lon",longitude).replace(":appid", apiKeyService.getRandomKey(ServiceType.OPENWEATHER));
        String object = restTemplate.getForObject(url, String.class);
        Map<String, Object> result = JsonFlattener.flattenAsMap(object);

        Double tempC =   ((Double.parseDouble(result.get("main.temp").toString()) - 32)*5)/9;

        Weather weather = new Weather()
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setLocation(result.get("name").toString())
                .setSunrise(result.get("sys.sunrise").toString())
                .setSunset(result.get("sys.sunset").toString())
                .setTemperatureC(tempC.toString())
                .setTemperatureF(result.get("main.temp").toString());

        return weather;
    }

}
