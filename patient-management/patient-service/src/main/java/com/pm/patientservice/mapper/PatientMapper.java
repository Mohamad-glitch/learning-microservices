package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.repository.Patient;

import java.time.LocalDate;

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

    public static Patient toPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();



        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setPhone(patientRequestDTO.getPhone());
        patient.setBirthDate(LocalDate.parse(patientRequestDTO.getBirthDate()));
        patient.setRegistrationDate(LocalDate.parse(patientRequestDTO.getRegistrationDate()));


        return patient;
    }


}
