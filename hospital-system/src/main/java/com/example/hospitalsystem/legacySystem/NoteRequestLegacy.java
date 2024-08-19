package com.example.hospitalsystem.legacySystem;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteRequestLegacy {
    private String agency;
    @NotNull(message = "DateFrom cannot be null")
    private LocalDate dateFrom;
    @NotNull(message = "DateTo cannot be null")
    private LocalDate dateTo;
    private String clientGuid;
}
