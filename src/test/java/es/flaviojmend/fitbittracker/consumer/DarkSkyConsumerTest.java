package es.flaviojmend.fitbittracker.consumer;

import es.flaviojmend.fitbittracker.TestAppConfiguration;
import es.flaviojmend.fitbittracker.TestMainApplication;
import es.flaviojmend.fitbittracker.persistence.entity.ApiKey;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import es.flaviojmend.fitbittracker.persistence.entity.Weather;
import es.flaviojmend.fitbittracker.persistence.repo.ApiKeyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestAppConfiguration.class, TestMainApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DarkSkyConsumerTest {


    @MockBean
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DarkSkyConsumer darkSkyConsumer;

    @Test
    public void retrieveKillorglinWeatherTest() {

        List<ApiKey> keys = new ArrayList<>();
        keys.add(new ApiKey()
                .setService(ServiceType.DARKSKY)
                .setKey("245e2e5cb29b32904812fcb335e52fe6"));

        given(this.apiKeyRepository.findAllByService(ServiceType.DARKSKY)).willReturn(keys);

        Weather weather = darkSkyConsumer.getWeatherByLatLong("52.1028845", "-9.7854155");
        assertEquals("Killorglin", weather.getLocation());
    }


}
