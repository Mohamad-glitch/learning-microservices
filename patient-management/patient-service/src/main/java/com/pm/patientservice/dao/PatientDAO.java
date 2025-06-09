package com.pm.patientservice.dao;

import com.pm.patientservice.repository.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientDAO extends JpaRepository<Patient, UUID> {
    Patient getPatientsById(UUID id);

    Patient getPatientsByEmail(String email);

    @Query("select p from Patient p")
    List<Patient> getAllPatients();

    void deletePatientByEmail(String email);

    void deletePatientByAddress(String address);
}
