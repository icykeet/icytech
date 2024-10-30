package ru.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.project.models.TechnologyDto;

import java.util.List;
import java.util.Optional;

public class TechnologyClientImpl implements TechnologyClient {

    private final String address;

    private final RestTemplate restTemplate;

    @Autowired
    public TechnologyClientImpl(@Value("${serviceTechnology.url}") String address,
                                @Qualifier("provideTechnologyRestTemplate") RestTemplate restTemplate) {
        this.address = address;
        this.restTemplate = restTemplate;
    }

    public TechnologyDto creation(TechnologyDto technologyDto) {
        HttpEntity<TechnologyDto> requestEntity = new HttpEntity<>(technologyDto);
        return restTemplate.exchange(address, HttpMethod.POST, requestEntity, TechnologyDto.class).getBody();
    }

    public TechnologyDto findById(int id) {
        return restTemplate.getForEntity(address + "/" + id, TechnologyDto.class).getBody();
    }

    public void deleteById(int id) {
        restTemplate.exchange(address + "/" + id, HttpMethod.DELETE, null, String.class);
    }

    public void changeRang(int id, String rang) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(address + "/" + id)
                .queryParam("rang", rang);
        String uri = builder.buildAndExpand().toString();
        restTemplate.exchange(uri, HttpMethod.PUT, null, String.class);
    }


    public List<TechnologyDto> getByCriteria(String id, String name, String technologyType, String ring) {
        return exchangeAsList(addressCreation(id, name, technologyType, ring), new ParameterizedTypeReference<>() {});
    }

    private  <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
    }
    private String addressCreation(String id, String name, String technologyType, String rang) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(address)
                .queryParamIfPresent("technologyType", Optional.ofNullable(technologyType))
                .queryParamIfPresent("rang", Optional.ofNullable(rang))
                .queryParamIfPresent("id", Optional.ofNullable(id))
                .queryParamIfPresent("name", Optional.ofNullable(name));
        return builder.buildAndExpand().toString();
    }
}
