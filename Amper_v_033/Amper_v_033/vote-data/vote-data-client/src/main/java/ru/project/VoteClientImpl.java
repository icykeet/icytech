package ru.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.project.models.TechnologyDto;

public class VoteClientImpl implements VoteClient {

    private final String address;

    private final RestTemplate restTemplate;



    @Autowired
    public VoteClientImpl(@Value("${serviceVote.url}") String address,
                          @Qualifier("provideTechnologyRestTemplate") RestTemplate restTemplate) {
        this.address = address;
        this.restTemplate = restTemplate;
    }
    public String vote(int userId, int technologyId, String rang) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(address)
                .queryParam("rang", rang)
                .queryParam("userId", userId)
                .queryParam("technologyId", technologyId);
        String uri = builder.buildAndExpand().toString();
        return restTemplate.exchange(uri, HttpMethod.PUT, null, String.class).getBody();
    }

    public void registerNewTechnology(TechnologyDto technologyDto) {
        HttpEntity<TechnologyDto> requestEntity = new HttpEntity<>(technologyDto);
        restTemplate.exchange(address, HttpMethod.POST, requestEntity, String.class);
    }

    public void removeTechnology(int technologyId) {
        restTemplate.exchange(address + "/" + technologyId, HttpMethod.DELETE, null, String.class);
    }
}
