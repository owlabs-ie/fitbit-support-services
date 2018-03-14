package es.flaviojmend.fitbittracker.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.flaviojmend.fitbittracker.persistence.entity.Weather;
import es.flaviojmend.fitbittracker.persistence.entity.annotations.Shortname;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class JSONUtils {

    public static String retrieveFieldsFromWeatherObject(String fields, Weather weather) throws JsonProcessingException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ObjectMapper mapper = new ObjectMapper();
        if(fields == null) {
            return mapper.writeValueAsString(weather);
        }

        JSONObject json = new JSONObject();
        for(String field : fields.split(",")) {
            for(Field decField : weather.getClass().getDeclaredFields()) {
                for(Shortname shortname : decField.getAnnotationsByType(Shortname.class)) {
                    if(Shortname.class.getMethod("value").invoke(shortname).equals(field)) {
                        json.put(field, weather.getClass().getMethod("get"+StringUtils.capitalize(decField.getName())).invoke(weather));
                    }
                }
            }


        }

        return json.toString();
    }

}
