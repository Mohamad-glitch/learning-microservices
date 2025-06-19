package com.pm.patientservice.service;


import com.pm.patientservice.dao.PatientDAO;
import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.AlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.grpc.BillingServiceGrpcClient;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.repository.Patient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImp implements PatientService {

    private final PatientDAO patientDAO;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    @Autowired
    public PatientServiceImp(PatientDAO patientDAO, BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientDAO = patientDAO;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
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
    public PatientResponseDTO createPatient(PatientRequestDTO patient) {
        if (patientDAO.existsPatientByEmail(patient.getEmail())) {
            throw new AlreadyExistsException("a patient with this email: " + patient.getEmail() + " already exists ");
        }

        Patient saved = patientDAO.save(PatientMapper.toPatient(patient));

        // in this code we will call the wanted service and let it make what we want(logic) then return the values
        billingServiceGrpcClient.createBillingAccount(saved.getId().toString(), saved.getName(), saved.getEmail());

        return PatientMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patient) {
        Patient patientToUpdate = patientDAO.findById(id).orElseThrow(() -> (new PatientNotFoundException("patient with id: " + id + " not found")));

        if (patientDAO.existsPatientByEmail(patient.getEmail())) {
            throw new AlreadyExistsException("a patient with this email: " + patient.getEmail() + " already exists ");
        }

        patientToUpdate.setName(patient.getName());
        patientToUpdate.setAddress(patient.getAddress());
        patientToUpdate.setBirthDate(LocalDate.parse(patient.getBirthDate()));
        patientToUpdate.setEmail(patient.getEmail());
        patientToUpdate.setPhone(patient.getPhone());

        patientToUpdate = patientDAO.save(patientToUpdate);

        return PatientMapper.toDTO(patientToUpdate);
    }

    @Override
    @Transactional
    public void deletePatientByEmail(String email) {
        patientDAO.deletePatientByEmail(email);

    }
}
