package com.example.hospitalsystem.legacySystem;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequestLegacy {
    private String agency;
    @NotNull(message = "DateFrom cannot be null")
    private LocalDate dateFrom;
    @NotNull(message = "DateTo cannot be null")
    private LocalDate dateTo;
    private String clientGuid;
}
