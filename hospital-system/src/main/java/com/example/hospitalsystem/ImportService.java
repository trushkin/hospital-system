package com.example.hospitalsystem;

import com.example.hospitalsystem.legacySystem.ClientDtoLegacy;
import com.example.hospitalsystem.legacySystem.LegacySystemService;
import com.example.hospitalsystem.legacySystem.NoteDtoLegacy;
import com.example.hospitalsystem.legacySystem.NoteRequestLegacy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ImportService {
    private final LegacySystemService legacySystemService;
    private final PatientProfileRepository patientProfileRepository;
    private final PatientNoteRepository patientNoteRepository;
    private final CompanyUserRepository companyUserRepository;
    private static final Set<Short> ACTIVE_STATUSES = Set.of((short) 200, (short) 210, (short) 230);

    public List<ActivePatientGuidWithAgency> fetchActivePatients() {
        List<ClientDtoLegacy> clientDtoLegacyList = legacySystemService.getAllClientFromLegacySystem();
        List<ActivePatientGuidWithAgency> activePatientsList = new ArrayList<>();
        for (ClientDtoLegacy c : clientDtoLegacyList) {
            Short status = patientProfileRepository.getStatusByOldClientGuid(c.getGuid());
            if (status != null && ACTIVE_STATUSES.contains(status)) {
                activePatientsList.add(new ActivePatientGuidWithAgency(c.getAgency(), c.getGuid()));
            }
        }
        return activePatientsList;
    }

    private void updateNote(NoteDtoLegacy legacyNote, CompanyUser companyUser, PatientNote patientNote) {
        patientNote.setLastModifiedDateTime(legacyNote.getModifiedDateTime());
        patientNote.setLastModifiedBy(companyUser);
        patientNote.setNote(legacyNote.getComments());
        patientNoteRepository.save(patientNote);
    }

    private void createNote(NoteDtoLegacy legacyNote, CompanyUser companyUser) {
        PatientNote patientNote = PatientNote.builder()
                .createdDateTime(legacyNote.getCreatedDateTime())
                .lastModifiedDateTime(legacyNote.getModifiedDateTime())
                .createdBy(companyUser)
                .lastModifiedBy(companyUser)
                .note(legacyNote.getComments())
                .oldNoteGuid(legacyNote.getGuid())
                .patientProfile(patientProfileRepository.findByOldClientGuid(legacyNote.getClientGuid()).get())
                .build();
        patientNoteRepository.save(patientNote);
    }

    public void importOrUpdateNotes(ActivePatientGuidWithAgency activePatient) {
        LocalDate testDate = LocalDate.of(2021, 11, 15);
        NoteRequestLegacy noteRequestLegacy = NoteRequestLegacy.builder()
                .agency(activePatient.getAgency())
                .clientGuid(activePatient.getGuid())
                .dateFrom(testDate)
                .dateTo(testDate.plusDays(15))
                .build();
        List<NoteDtoLegacy> noteDtoLegacyList = legacySystemService.getNotesFromLegacySystem(noteRequestLegacy);
        for (NoteDtoLegacy legacyNote : noteDtoLegacyList) {
            if (patientNoteRepository.findByOldNoteGuid(legacyNote.getGuid()).isPresent()) {
                PatientNote patientNote = patientNoteRepository.findByOldNoteGuid(legacyNote.getGuid()).get();
                if (legacyNote.getModifiedDateTime().isAfter(patientNote.getLastModifiedDateTime())) {
                    CompanyUser companyUser = patientNote.getCreatedBy();
                    if (!companyUserRepository.existsByLogin(legacyNote.getLoggedUser())) {
                        companyUser = companyUserRepository.save(new CompanyUser(legacyNote.getLoggedUser()));
                    }
                    updateNote(legacyNote, companyUser, patientNote);
                }
            } else {
                CompanyUser companyUser;
                if (companyUserRepository.findByLogin(legacyNote.getLoggedUser()).isPresent()) {
                    companyUser = companyUserRepository.findByLogin(legacyNote.getLoggedUser()).get();
                } else {
                    companyUser = companyUserRepository.save(new CompanyUser(legacyNote.getLoggedUser()));
                }
                createNote(legacyNote, companyUser);
            }
        }
    }

}
