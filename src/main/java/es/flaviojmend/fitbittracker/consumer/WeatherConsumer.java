package es.flaviojmend.fitbittracker.consumer;

import es.flaviojmend.fitbittracker.persistence.entity.Weather;

public interface WeatherConsumer {

    public Weather getWeatherByLatLong(String latitude, String longitude);

}
