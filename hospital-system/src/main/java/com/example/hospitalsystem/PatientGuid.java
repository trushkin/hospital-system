package com.example.hospitalsystem;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patient_guid")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PatientGuid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "old_client_guid")
    private String oldClientGuid;
    @ManyToOne
    @JoinColumn(name = "patient_profile_id")
    private PatientProfile patientProfile;
}
