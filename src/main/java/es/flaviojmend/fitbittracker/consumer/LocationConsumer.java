package es.flaviojmend.fitbittracker.consumer;

import com.github.wnameless.json.flattener.JsonFlattener;
import es.flaviojmend.fitbittracker.persistence.entity.Location;
import es.flaviojmend.fitbittracker.persistence.entity.ServiceType;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

@Component
public class LocationConsumer {

    private static final String ENDPOINT = "https://nominatim.openstreetmap.org/reverse?email=myemail@myserver.com&lat={latitude}&lon={longitude}&format=json&accept-language=en-US";

    public RestTemplate getRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        };
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

    public Location getWeatherByLatLong(String latitude, String longitude) {

        try {
            String object = getRestTemplate().getForObject(ENDPOINT, String.class, latitude, longitude);

            Map<String, Object> result = JsonFlattener.flattenAsMap(object);

            return new Location().setLatitude(latitude)
                    .setLongitude(longitude)
                    .setCountry(result.get("address.country") != null ? result.get("address.country").toString() : "Unknown")
                    .setDescription(getLocationDescription(result));
        } catch (Exception e) {
            return new Location().setLatitude(latitude)
                    .setLongitude(longitude)
                    .setCountry("Unknown");
        }
    }

    private String getLocationDescription(Map<String, Object> map) {
        if (map.get("address.hamlet") != null) {
            return map.get("address.hamlet").toString();
        } else if(map.get("address.village") != null) {
            return map.get("address.village").toString();
        } else if(map.get("address.city_district") != null) {
            return map.get("address.city_district").toString();
        } else if(map.get("address.town") != null) {
            return map.get("address.town").toString();
        } else if(map.get("address.city") != null) {
            return map.get("address.city").toString();
        }
        return "";
    }

}
