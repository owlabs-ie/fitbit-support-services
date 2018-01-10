package es.flaviojmend.fitbittracker.consumer;

import com.github.wnameless.json.flattener.JsonFlattener;
import es.flaviojmend.fitbittracker.persistence.entity.Location;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class LocationConsumer {

    private static final String ENDPOINT = "https://nominatim.openstreetmap.org/reverse?lat=:lat&lon=:lon&format=json&accept-language=en-US";
    private RestTemplate restTemplate = new RestTemplate();

    public Location getWeatherByLatLong(String latitude, String longitude) {
        String url = ENDPOINT.replace(":lat", latitude).replace(":lon",longitude);
        String object = restTemplate.getForObject(url, String.class);
        Map<String, Object> result = JsonFlattener.flattenAsMap(object);

        return new Location().setLatitude(latitude)
                            .setLongitude(longitude)
                            .setDescription(result.get("address.city").toString());

    }

}
