package es.flaviojmend.fitbittracker.service;


import es.flaviojmend.fitbittracker.TestAppConfiguration;
import es.flaviojmend.fitbittracker.TestMainApplication;
import es.flaviojmend.fitbittracker.persistence.entity.ApiKey;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.repo.ApiKeyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestAppConfiguration.class, TestMainApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiKeyServiceTest {


    @MockBean
    private ApiKeyRepository apiKeyRepository;


    public static final String API_KEY = "111111";
    @Autowired
    private ApiKeyService apiKeyService;

    @Test
    public void insertKeyTest() {

        List<ApiKey> keys = new ArrayList<>();
        ApiKey apiKey = new ApiKey()
                .setService(ServiceType.DARKSKY)
                .setKey(API_KEY);

        keys.add(apiKey);



        given(this.apiKeyRepository.findAllByService(ServiceType.DARKSKY)).willReturn(keys);
        given(this.apiKeyRepository.findOne(API_KEY)).willReturn(apiKey);


        apiKeyService.addApiKeys(API_KEY, "DARKSKY");

        ApiKey apiKeyRetrieved = apiKeyService.getKey(API_KEY);
        assertNotNull(apiKeyRetrieved);

        apiKeyService.removeApiKey(API_KEY);
    }

}
