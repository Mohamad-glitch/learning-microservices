package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.repository.Patient;

import java.util.List;
import java.util.UUID;

public interface PatientService {

    Patient getPatientById(UUID id);

    Patient getPatientByEmail(String email);

    List<PatientResponseDTO> getAllPatients();

    PatientResponseDTO createPatient(PatientRequestDTO patient);

    void deletePatientByEmail(String email);

    PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patient);

}
