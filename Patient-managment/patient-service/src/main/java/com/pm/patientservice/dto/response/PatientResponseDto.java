package com.pm.patientservice.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PatientResponseDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String dateOfBirth;
}
