package es.flaviojmend.fitbittracker.persistence.entity;

import org.springframework.data.annotation.Id;

public class Location {

    @Id
    private String id;

    private String latitude;
    private String longitude;
    private String description;

    public String getId() {
        return id;
    }

    public Location setId(String id) {
        this.id = id;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public Location setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public Location setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Location setDescription(String description) {
        this.description = description;
        return this;
    }
}
