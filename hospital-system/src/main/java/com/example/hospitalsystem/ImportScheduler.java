package com.example.hospitalsystem;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImportScheduler {
    private final ImportService importService;

    // @Scheduled(cron = "0 15 */2 * * *")
    @Scheduled(fixedDelay = 10000)
    public void scheduleImport() {
        List<ActivePatientGuidWithAgency> activePatientsList = importService.fetchActivePatients();
        activePatientsList.forEach(importService::importOrUpdateNotes);
    }
}
