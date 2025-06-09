package com.pm.patientservice.controller;


import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/patient")
@RestController
public class restController {
    private static final Logger log = LoggerFactory.getLogger(restController.class);
    // CRUD -> Create, Read, Update and Delete
    private final PatientService patientService;

    public restController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/get-all-patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();

        return ResponseEntity.ok().body(patients);
    }


    @PostMapping("/add-patient")
    public ResponseEntity<PatientResponseDTO> addPatient(@RequestBody PatientResponseDTO patient) {
        PatientResponseDTO result = patientService.createPatient(patient);

        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping("/delete-patient/{email}")
    public ResponseEntity<String> deletePatient(@PathVariable String email) {
        log.info("Delete patient has email: {}", email);
        patientService.deletePatientByEmail(email);
        log.info("Delete patient has email: {}", email);

        return ResponseEntity.ok().build();
    }


}
