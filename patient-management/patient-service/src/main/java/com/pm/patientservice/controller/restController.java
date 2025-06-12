package com.pm.patientservice.controller;


import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/patient")
@RestController
@Tag(name = "patient", description = "patient CRUD operation")
public class restController {
    private static final Logger log = LoggerFactory.getLogger(restController.class);
    // CRUD -> Create, Read, Update and Delete
    private final PatientService patientService;

    public restController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/get-all-patients")
    @Operation(summary = "get all patient")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();

        return ResponseEntity.ok().body(patients);
    }


    @PostMapping("/add-patient")
    @Operation(summary = "create new patient")
    public ResponseEntity<PatientResponseDTO> addPatient(@Valid @RequestBody PatientRequestDTO patient) {
        PatientResponseDTO result = patientService.createPatient(patient);

        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update a patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable("id") UUID id, @Valid @RequestBody PatientRequestDTO patient) {
        PatientResponseDTO responseDTO = patientService.updatePatient(id, patient);

        return ResponseEntity.ok().body(responseDTO);
    }


    @DeleteMapping("/delete-patient/{email}")
    @Operation(summary = "delete a patient with id")
    public ResponseEntity<String> deletePatient(@PathVariable String email) {
        log.info("Delete patient has email: {}", email);
        patientService.deletePatientByEmail(email);
        log.info("Delete patient has email: {}", email);

        return ResponseEntity.ok().build();
    }


}
