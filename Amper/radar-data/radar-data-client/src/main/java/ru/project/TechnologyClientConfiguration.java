package ru.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(TechnologyClientImpl.class)
public class TechnologyClientConfiguration {
    private final RestTemplate restTemplate;

    @Autowired
    public TechnologyClientConfiguration(@Value("${serviceTechnology.url}")String address, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri(address).build();
    }

    @Bean
    public RestTemplate provideTechnologyRestTemplate() {
        return restTemplate;
    }
}