package com.pm.patientservice.repository;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString


@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String name;


    @Email
    @NotNull
    @Column(unique = true)
    private String email;


    @NotNull
    private String phone;

    @NotNull
    private String address;

    @NotNull
    private LocalDate birthDate;


    @NotNull
    private LocalDate registrationDate;


}
