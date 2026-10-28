package com.pm.patientservice.service;

import com.pm.patientservice.dto.request.PatientRequestDTO;
import com.pm.patientservice.dto.response.PatientResponseDto;

import java.util.List;
import java.util.UUID;

public interface PatientService {

     List<PatientResponseDto> getPatients();


    PatientResponseDto createPatient(PatientRequestDTO requestDTO);
    void deletePatient(UUID id);

    PatientResponseDto updatePatient(UUID id, PatientRequestDTO requestDTO);
}
