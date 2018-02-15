package es.flaviojmend.fitbittracker.consumer;

import com.github.wnameless.json.flattener.JsonFlattener;
import es.flaviojmend.fitbittracker.persistence.entity.Location;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class LocationConsumer {

    private static final String ENDPOINT = "https://nominatim.openstreetmap.org/reverse?email=myemail@myserver.com&lat={latitude}&lon={longitude}&format=json&accept-language=en-US";

    private RestTemplate restTemplate = new RestTemplate();

    public Location getWeatherByLatLong(String latitude, String longitude) {

        try {
            String object = restTemplate.getForObject(ENDPOINT, String.class, latitude, longitude);

            Map<String, Object> result = JsonFlattener.flattenAsMap(object);

            return new Location().setLatitude(latitude)
                    .setLongitude(longitude)
                    .setCountry(result.get("address.country") != null ? result.get("address.country").toString() : "Unknown")
                    .setDescription(getLocationDescription(result));
        } catch (Exception e) {
            return new Location().setLatitude(latitude)
                    .setLongitude(longitude)
                    .setCountry("Unknown");
        }
    }

    private String getLocationDescription(Map<String, Object> map) {
        if (map.get("address.hamlet") != null) {
            return map.get("address.hamlet").toString();
        } else if(map.get("address.village") != null) {
            return map.get("address.village").toString();
        } else if(map.get("address.city_district") != null) {
            return map.get("address.city_district").toString();
        } else if(map.get("address.town") != null) {
            return map.get("address.town").toString();
        } else if(map.get("address.city") != null) {
            return map.get("address.city").toString();
        }
        return "";
    }

}
