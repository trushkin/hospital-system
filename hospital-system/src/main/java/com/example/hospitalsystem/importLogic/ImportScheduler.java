package com.example.hospitalsystem.importLogic;

import com.example.hospitalsystem.ActivePatientGuidWithAgency;
import com.example.hospitalsystem.StatisticCounter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImportScheduler {
    private final ImportService importService;
    private static final Logger log = LoggerFactory.getLogger(ImportScheduler.class);

    @Scheduled(cron = "0 15 */2 * * *")
    public void scheduleImport() {
        List<ActivePatientGuidWithAgency> activePatientsList = importService.fetchActivePatients();
        StatisticCounter statisticCounter = new StatisticCounter();
        for (ActivePatientGuidWithAgency a : activePatientsList) {
            StatisticCounter temp = importService.importOrUpdateNotes(a);
            statisticCounter.setCreatedNotes(statisticCounter.getCreatedNotes() + temp.getCreatedNotes());
            statisticCounter.setUpdatedNotes(statisticCounter.getUpdatedNotes() + temp.getUpdatedNotes());
        }
        log.info("""
                                    
                Import has finished by:""" + LocalDateTime.now() + """
                                    
                Updated notes:""" + statisticCounter.getUpdatedNotes() + """
                                    
                Created notes:""" + statisticCounter.getCreatedNotes());
    }
}
