package com.example.hospitalsystem;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_note")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PatientNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;
    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;
    @Column
    private String note;
    @Column(name = "old_note_guid")
    private String oldNoteGuid;
    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private CompanyUser createdBy;
    @ManyToOne
    @JoinColumn(name = "last_modified_by_user_id")
    private CompanyUser lastModifiedBy;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientProfile patientProfile;

}
