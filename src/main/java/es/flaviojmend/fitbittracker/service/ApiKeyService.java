package es.flaviojmend.fitbittracker.service;

import es.flaviojmend.fitbittracker.persistence.entity.ApiKey;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.repo.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public Iterable<ApiKey> listApiKeys() {
        return apiKeyRepository.findAll();
    }

    public void addApiKeys(String apiKeys, String service) {
        for(String key:apiKeys.split(",")){
            ApiKey apiKey = new ApiKey();
            apiKey.setKey(key.trim());
            apiKey.setService(ServiceType.valueOf(service));
            apiKeyRepository.save(apiKey);
        }
    }

    public ApiKey getKey(String apiKey) {
        return apiKeyRepository.findOne(apiKey);
    }

    public String getRandomKey(ServiceType serviceType) {
        List<ApiKey> keys = apiKeyRepository.findAllByService(serviceType);
        Random rand = new Random();
        return keys.get(rand.nextInt(keys.size())).getKey();
    }

    public void removeApiKey(String id) {
        apiKeyRepository.delete(id);
    }
}
