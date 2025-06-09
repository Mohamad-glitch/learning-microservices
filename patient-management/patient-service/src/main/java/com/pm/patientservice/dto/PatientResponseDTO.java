package com.pm.patientservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class PatientResponseDTO {


    private String id;

    @NotNull
    @Size(min = 5, max = 50)
    private String name;

    @NotNull
    private String email;

    @NotNull
    @Size(min = 10, max = 10)
    private String phone;

    @NotNull
    private String address;

    @NotNull
    private String birthDate;

    @NotNull
    private String registrationDate;


}
