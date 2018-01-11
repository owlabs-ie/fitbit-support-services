package es.flaviojmend.fitbittracker.service;


import es.flaviojmend.fitbittracker.persistence.entity.ApiKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiKeyServiceTest {

    public static final String API_KEY = "111111";
    @Autowired
    private ApiKeyService apiKeyService;

    @Test
    public void insertKeyTest() {
        apiKeyService.addApiKeys(API_KEY, "DARKSKY");

        ApiKey apiKey = apiKeyService.getKey(API_KEY);
        assertNotNull(apiKey);

        apiKeyService.removeApiKey(API_KEY);
        ApiKey apiKeyRemoved = apiKeyService.getKey(API_KEY);
        assertNull(apiKeyRemoved);
    }

}
