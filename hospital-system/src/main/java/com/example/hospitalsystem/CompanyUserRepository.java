package com.example.hospitalsystem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyUserRepository extends JpaRepository<CompanyUser, Long> {
    boolean existsByLogin(String login);
    Optional<CompanyUser> findByLogin(String login);
}
