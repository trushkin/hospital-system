package com.example.hospitalsystem.legacySystem;

import com.example.hospitalsystem.legacySystem.ClientDtoLegacy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

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

    public List<NoteDtoLegacy> getNotesFromLegacySystem(NoteRequestLegacy noteRequestLegacy) {
        return restClient.post()
                .uri("/notes")
                .contentType(APPLICATION_JSON)
                .body(noteRequestLegacy)
                .retrieve()
                .body(new ParameterizedTypeReference<List<NoteDtoLegacy>>() {
                });
    }

}
