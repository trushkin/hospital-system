package com.example.hospitalsystem;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class LegacySystemService {
    private final RestClient restClient;

    public LegacySystemService() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    public List<ClientDtoLegacy> getAllClientFromLegacySystem() {
        return restClient.post()
                .uri("/clients")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ClientDtoLegacy>>() {
                });
    }
}
