package com.example.hospitalsystem;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patient_profile")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PatientProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "status_id")
    private Short statusId;
}
