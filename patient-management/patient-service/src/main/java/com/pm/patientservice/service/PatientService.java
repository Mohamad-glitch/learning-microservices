package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.repository.Patient;

import java.util.List;
import java.util.UUID;

public interface PatientService {

    Patient getPatientById(UUID id);

    Patient getPatientByEmail(String email);

    List<PatientResponseDTO> getAllPatients();

    PatientResponseDTO createPatient(PatientResponseDTO patient);

    void deletePatientByEmail(String email);

}
