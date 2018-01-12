package es.flaviojmend.fitbittracker.consumer;

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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpenWeatherConsumerTest {


    @MockBean
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OpenWeatherConsumer openWeatherConsumer;

    @Test
    public void retrieveKillorglinWeatherTest() {

        List<ApiKey> keys = new ArrayList<>();
        keys.add(new ApiKey()
                .setService(ServiceType.OPENWEATHER)
                .setKey("cc2040e41b14bd08429f8c99eef4acd6"));

        given(this.apiKeyRepository.findAllByService(ServiceType.OPENWEATHER)).willReturn(keys);

        Weather weather = openWeatherConsumer.getWeatherByLatLong("52.1028845", "-9.7854155");
        assertEquals("Killorglin", weather.getLocation());
    }


}
