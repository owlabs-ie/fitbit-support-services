package es.flaviojmend.fitbittracker.persistence.entity;

import java.util.List;

public class WeatherRequestStats {

    private Integer count;
    private List<WeatherRequest> requestsList;

    public Integer getCount() {
        return count;
    }

    public WeatherRequestStats setCount(Integer count) {
        this.count = count;
        return this;
    }

    public List<WeatherRequest> getRequestsList() {
        return requestsList;
    }

    public WeatherRequestStats setRequestsList(List<WeatherRequest> requestsList) {
        this.requestsList = requestsList;
        return this;
    }
}
