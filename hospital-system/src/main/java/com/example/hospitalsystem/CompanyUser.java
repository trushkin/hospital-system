package com.example.hospitalsystem;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String login;
}
