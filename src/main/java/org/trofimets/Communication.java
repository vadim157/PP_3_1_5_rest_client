package org.trofimets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.trofimets.entity.User;

import java.util.List;

@Component
public class Communication {


    private RestTemplate restTemplate;

    private final String URL = "http://94.198.50.185:7081/api/users";

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        HttpHeaders headers = response.getHeaders();
        String cookie = headers.getFirst("Set-Cookie");
        System.out.println(response.getBody());
        System.out.println("SessionID " + cookie);
        return cookie;
    }

    public void saveUser(HttpHeaders headers, User user) {

        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        HttpEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class);
        System.out.println(response.getBody());
    }

    public void updateUser(HttpHeaders headers, User userUpdate) {

        HttpEntity<User> httpEntity = new HttpEntity<>(userUpdate, headers);
        HttpEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, httpEntity, String.class);
        System.out.println(response.getBody());
    }

    public String delete(HttpHeaders headers, Long id) {
        HttpEntity httpEntity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, httpEntity, String.class);
        return response.getBody();
    }

}
