package com.example.hospitalsystem;

import com.example.hospitalsystem.legacySystem.ClientDtoLegacy;
import com.example.hospitalsystem.legacySystem.LegacySystemService;
import com.example.hospitalsystem.legacySystem.NoteDtoLegacy;
import com.example.hospitalsystem.legacySystem.NoteRequestLegacy;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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
    private static final Set<Short> ACTIVE_STATUSES = Set.of((short) 200, (short) 210, (short) 230);

    //@Scheduled(fixedDelay = 2000)
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
    @Scheduled(fixedDelay = 2000)
    public void test() {
        NoteRequestLegacy noteRequestLegacy = new NoteRequestLegacy("agency2", LocalDate.of(2021, 11, 18),
                LocalDate.of(2021, 11, 28), "A2C3D4F6-G7H8-I9J0-K1L2-M3N4O5P6Q7R8");
        List<NoteDtoLegacy> noteDtoLegacyList = legacySystemService.getNotesFromLegacySystem(noteRequestLegacy);
    }
}
