package es.flaviojmend.fitbittracker.consumer;

import es.flaviojmend.fitbittracker.persistence.entity.Weather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DarkSkyConsumerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DarkSkyConsumer darkSkyConsumer;

    @Test
    public void retrieveKillorglinWeatherTest() {

        Weather weather = darkSkyConsumer.getWeatherByLatLong("52.1028845", "-9.7854155");
        assertEquals("Killorglin", weather.getLocation());
    }


}
