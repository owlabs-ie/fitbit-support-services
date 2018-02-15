package es.flaviojmend.fitbittracker.consumer;

import es.flaviojmend.fitbittracker.persistence.entity.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationConsumerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LocationConsumer locationConsumer;

    @Test
    public void retrieveKillorglinLocationTest() {

        Location location = locationConsumer.getWeatherByLatLong("52.1028845", "-9.7854155");

        System.out.println(location);
        assertEquals("Killorglin", location.getDescription());
    }


}
