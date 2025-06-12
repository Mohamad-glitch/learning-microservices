package com.pm.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class PatientRequestDTO {
// this kind of validation because DTO has a slit diff in validation than repository

    @NotBlank(message = "name is required")
    @Size(max = 100, message = "the name should be less than 100 character")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "should be valid")
    private String email;

    @NotBlank(message = "phone is required")
    private String phone;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "date of birth is required")
    private String birthDate;

    @NotBlank(message = "registration date is required")
    private String registrationDate;


}
