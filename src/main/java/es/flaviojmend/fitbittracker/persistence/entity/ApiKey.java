package es.flaviojmend.fitbittracker.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "apiKeys")
public class ApiKey {

    @Id
    private String key;
    private ServiceType service;

    public ServiceType getService() {
        return service;
    }

    public ApiKey setService(ServiceType service) {
        this.service = service;
        return this;
    }

    public String getKey() {

        return key;
    }

    public ApiKey setKey(String key) {
        this.key = key;
        return this;
    }
}
