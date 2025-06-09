package com.pm.patientservice.service;


import com.pm.patientservice.dao.PatientDAO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.repository.Patient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImp implements PatientService {

    private PatientDAO patientDAO;

    @Autowired
    public PatientServiceImp(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }



    @Override
    public Patient getPatientById(UUID id) {
        return patientDAO.getPatientsById(id);
    }

    @Override
    public Patient getPatientByEmail(String email) {
        return patientDAO.getPatientsByEmail(email);
    }

    @Override
    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientDAO.getAllPatients();


        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public PatientResponseDTO createPatient(PatientResponseDTO patient) {
        System.out.println(patient);
        patientDAO.save(PatientMapper.toPatient(patient));

        Patient saved = patientDAO.getPatientsByEmail(patient.getEmail());

        return PatientMapper.toDTO(saved);
    }


    @Override
    @Transactional
    public void deletePatientByEmail(String email) {
        patientDAO.deletePatientByEmail(email);

    }
}
