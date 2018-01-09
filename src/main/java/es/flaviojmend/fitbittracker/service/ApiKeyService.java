package es.flaviojmend.fitbittracker.service;

import es.flaviojmend.fitbittracker.persistence.entity.ApiKey;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.repo.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public Iterable<ApiKey> listApiKeys() {
        return apiKeyRepository.findAll();
    }

    public void addApiKeys(String apiKeys) {
        for(String key:apiKeys.split(",")){
            ApiKey apiKey = new ApiKey();
            apiKey.setKey(key);
            apiKey.setService(ServiceType.OPENWEATHER);
            apiKeyRepository.save(apiKey);
        }
    }

    public String getRandomKey() {
        Iterable<ApiKey> keys = apiKeyRepository.findAll();
        ArrayList<ApiKey> keysList = new ArrayList<>();
        keys.forEach(keysList::add);
        Random rand = new Random();

        return keysList.get(rand.nextInt(keysList.size())).getKey();
    }
}
