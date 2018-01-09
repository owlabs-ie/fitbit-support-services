package es.flaviojmend.fitbittracker.persistence.entity;

import org.springframework.data.annotation.Id;

public class Weather {

    @Id
    private String id;

    private String latitude;
    private String longitude;
    private String temperatureC;
    private String temperatureF;
    private String location;
    private Boolean isDay;
    private String sunrise;
    private String sunset;

    public String getLatitude() {
        return latitude;
    }

    public Weather setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public Weather setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getTemperatureC() {
        return temperatureC;
    }

    public Weather setTemperatureC(String temperatureC) {
        this.temperatureC = temperatureC;
        return this;
    }

    public String getTemperatureF() {
        return temperatureF;
    }

    public Weather setTemperatureF(String temperatureF) {
        this.temperatureF = temperatureF;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Weather setLocation(String location) {
        this.location = location;
        return this;
    }

    public Boolean getDay() {
        return isDay;
    }

    public Weather setDay(Boolean day) {
        isDay = day;
        return this;
    }

    public String getSunrise() {
        return sunrise;
    }

    public Weather setSunrise(String sunrise) {
        this.sunrise = sunrise;
        return this;
    }

    public String getSunset() {
        return sunset;
    }

    public Weather setSunset(String sunset) {
        this.sunset = sunset;
        return this;
    }
}
