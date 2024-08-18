package com.example.hospitalsystem;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ImportService {
    private final LegacySystemService legacySystemService;
    private final PatientProfileRepository patientProfileRepository;
    private static final Set<Short> ACTIVE_STATUSES = Set.of((short) 200, (short) 210, (short) 230);

    @Scheduled(fixedDelay = 2000)
    public List<ActivePatientGuidWithAgency> fetchActivePatients() {
        List<ClientDtoLegacy> clientDtoLegacyList = legacySystemService.getAllClientFromLegacySystem();
        List<ActivePatientGuidWithAgency> activePatientsList = new ArrayList<>();
        for (ClientDtoLegacy c : clientDtoLegacyList) {
            Short status = patientProfileRepository.getStatusByOldClientGuid(c.getGuid());
            if(status != null && ACTIVE_STATUSES.contains(status)){
                activePatientsList.add(new ActivePatientGuidWithAgency(c.getAgency(), c.getGuid()));
            }
        }
        return activePatientsList;
    }
}
