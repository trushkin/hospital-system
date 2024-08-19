package com.example.hospitalsystem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientNoteRepository extends JpaRepository<PatientNote, Long> {
    Optional<PatientNote> findByOldNoteGuid(String oldNoteGuid);
}
