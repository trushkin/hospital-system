package com.example.hospitalsystem.legacySystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDtoLegacy {
    private String comments;
    private String guid;
    private LocalDateTime modifiedDateTime;
    private String clientGuid;
    private LocalDateTime datetime;
    private String loggedUser;
    private LocalDateTime createdDateTime;
}
