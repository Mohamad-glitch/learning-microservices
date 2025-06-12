package com.pm.patientservice.dto;

import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class PatientResponseDTO {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String birthDate;
    private String registrationDate;

}
