package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.repository.Patient;

import java.time.LocalDate;
import java.util.UUID;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO result = new PatientResponseDTO();

        result.setId(String.valueOf(patient.getId()));
        result.setName(patient.getName());
        result.setEmail(patient.getEmail());
        result.setAddress(patient.getAddress());
        result.setPhone(patient.getPhone());
        result.setBirthDate(String.valueOf(patient.getBirthDate()));
        result.setRegistrationDate(String.valueOf(patient.getRegistrationDate()));


        return result;
    }

    public static Patient toPatient(PatientResponseDTO patientResponseDTO) {
        Patient patient = new Patient();


        if (patientResponseDTO.getId() != null) {
            patient.setId(UUID.fromString(patientResponseDTO.getId()));
        }

        patient.setName(patientResponseDTO.getName());
        patient.setEmail(patientResponseDTO.getEmail());
        patient.setAddress(patientResponseDTO.getAddress());
        patient.setPhone(patientResponseDTO.getPhone());
        patient.setBirthDate(LocalDate.parse(patientResponseDTO.getBirthDate()));
        patient.setRegistrationDate(LocalDate.parse(patientResponseDTO.getRegistrationDate()));


        return patient;
    }


}
