package com.example.hospitalsystem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDtoLegacy {
    @JsonProperty("agency")
    private String agency;
    @JsonProperty("guid")
    private String guid;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("status")
    private String status;
    @JsonProperty("dob")
    private LocalDate dob;
    @JsonProperty("createdDateTime")
    private LocalDateTime createdDateTime;
}
