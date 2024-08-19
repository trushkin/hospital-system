package com.example.hospitalsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long> {
    @Query(nativeQuery = true, value = """
            select status_id from patient_profile
            join patient_guid pg on patient_profile.id = pg.patient_profile_id
            where pg.old_client_guid = :oldclientguid
            """)
    Short getStatusByOldClientGuid(String oldclientguid);

    @Query(nativeQuery = true, value = """
            select p.id, p.first_name, p.last_name, p.status_id from patient_profile p
            join patient_guid pg on p.id = pg.patient_profile_id
            where pg.old_client_guid = :oldclientguid
            """)
    Optional<PatientProfile> findByOldClientGuid(String oldclientguid);
}
