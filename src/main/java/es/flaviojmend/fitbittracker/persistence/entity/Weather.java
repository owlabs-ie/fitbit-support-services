package es.flaviojmend.fitbittracker.persistence.entity;

import es.flaviojmend.fitbittracker.persistence.entity.annotations.Shortname;
import org.springframework.data.annotation.Id;

public class Weather {

    @Id
    @Shortname("id")
    private String id;

    @Shortname("lat")
    private String latitude;

    @Shortname("lon")
    private String longitude;

    @Shortname("tpC")
    private String temperatureC;

    @Shortname("apC")
    private String apparentTemperatureC;

    @Shortname("tpF")
    private String temperatureF;

    @Shortname("apF")
    private String apparentTemperatureF;

    @Shortname("loc")
    private String location;

    @Shortname("sri")
    private String sunrise;

    @Shortname("sse")
    private String sunset;

    @Shortname("hum")
    private String humidity;

    @Shortname("prc")
    private String precipitation;

    @Shortname("con")
    private String condition;

    public String getCondition() {
        return condition;
    }

    public Weather setCondition(String condition) {
        this.condition = condition;
        return this;
    }

    public String getHumidity() {
        return humidity;
    }

    public Weather setHumidity(String humidity) {
        this.humidity = humidity;
        return this;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public Weather setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
        return this;
    }

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

    public String getId() {
        return id;
    }

    public Weather setId(String id) {
        this.id = id;
        return this;
    }

    public String getApparentTemperatureC() {
        return apparentTemperatureC;
    }

    public Weather setApparentTemperatureC(String apparentTemperatureC) {
        this.apparentTemperatureC = apparentTemperatureC;
        return this;
    }

    public String getApparentTemperatureF() {
        return apparentTemperatureF;
    }

    public Weather setApparentTemperatureF(String apparentTemperatureF) {
        this.apparentTemperatureF = apparentTemperatureF;
        return this;
    }

    public Weather setSunset(String sunset) {
        this.sunset = sunset;
        return this;
    }
}
