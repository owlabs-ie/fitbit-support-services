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
public class DarkSkyConsumer implements WeatherConsumer {

    @Autowired
    private ApiKeyService apiKeyService;

    private RestTemplate restTemplate = new RestTemplate();

    private static final String ENDPOINT = "https://api.darksky.net/forecast/:appid/:lat,:lon?exclude=minutely,hourly,alerts,flags";



    @Override
    public Weather getWeatherByLatLong(String latitude, String longitude) {
        String url = ENDPOINT.replace(":lat", latitude).replace(":lon",longitude).replace(":appid", apiKeyService.getRandomKey(ServiceType.DARKSKY));
        String object = restTemplate.getForObject(url, String.class);
        Map<String, Object> result = JsonFlattener.flattenAsMap(object);

        Double tempC = ((Double.parseDouble(result.get("currently.temperature").toString()) - 32)*5)/9;
        Double apparentTempC = ((Double.parseDouble(result.get("currently.apparentTemperature").toString()) - 32)*5)/9;

        Weather weather = new Weather()
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setLocation("")
                .setSunrise(result.get("daily.data[0].sunriseTime").toString())
                .setSunset(result.get("daily.data[0].sunsetTime").toString())
                .setTemperatureC(tempC.toString())
                .setApparentTemperatureC(apparentTempC.toString())
                .setTemperatureF(result.get("currently.temperature").toString())
                .setApparentTemperatureF(result.get("currently.apparentTemperature").toString());


        return weather;
    }

}
