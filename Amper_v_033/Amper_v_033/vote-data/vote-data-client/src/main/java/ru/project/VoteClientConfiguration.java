package ru.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(VoteClientImpl.class)
public class VoteClientConfiguration {
    private final RestTemplate restTemplate;

    @Autowired
    public VoteClientConfiguration(@Value("${serviceVote.url}")String address, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri(address).build();
    }

    @Bean
    public RestTemplate provideVoteRestTemplate() {
        return restTemplate;
    }
}
