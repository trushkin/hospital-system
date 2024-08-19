package com.example.hospitalsystem.patientNote;

import com.example.hospitalsystem.patientNote.PatientNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientNoteRepository extends JpaRepository<PatientNote, Long> {
    Optional<PatientNote> findByOldNoteGuid(String oldNoteGuid);
}
