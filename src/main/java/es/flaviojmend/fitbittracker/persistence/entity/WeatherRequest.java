package es.flaviojmend.fitbittracker.persistence.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "weatherRequests")
public class WeatherRequest {

    @Id
    private String id;
    private String latitude;
    private String longitude;
    private String location;
    private ServiceType service;
    private Date requestDate;
    private String app;

    public String getLocation() {
        return location;
    }

    public WeatherRequest setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getApp() {
        return app;
    }

    public WeatherRequest setApp(String app) {
        this.app = app;
        return this;
    }

    public String getId() {
        return id;
    }

    public WeatherRequest setId(String id) {
        this.id = id;
        return this;
    }

    public ServiceType getService() {
        return service;
    }

    public WeatherRequest setService(ServiceType service) {
        this.service = service;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public WeatherRequest setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public WeatherRequest setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public WeatherRequest setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
        return this;
    }
}
