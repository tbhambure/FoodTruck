package main.java.com.foodtrucks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class FoodTruckClient {

    RestTemplate restTemplate = new RestTemplate();

    public FoodTruck[] getFoodTrucks(int offset, int limit) {

        try {
            URI uri = getUri(offset, limit);

            FoodTruck[] foodTrucks = restTemplate.exchange(uri, HttpMethod.GET, getEntity(), FoodTruck[].class).getBody();
            return foodTrucks;

        } catch (HttpStatusCodeException e) {
            log.error("Could not retrieve data {}", e.getMessage());
            return null;
        }
    }

    private HttpEntity<?> getEntity() {
        final String contentType = "application/json";
        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", contentType);
        return (HttpEntity<?>) new HttpEntity(headers);
    }

    private URI getUri(int offset, int limit) {
        return UriComponentsBuilder.newInstance()
                    .scheme("http")
                    .host("data.sfgov.org")
                    .path("/resource/bbb8-hzi6.json")
                    .queryParam("$offset", offset)
                    .queryParam("$limit", limit)
                    .queryParam("$order", "applicant")
                    .build()
                    .toUri();
    }
}
