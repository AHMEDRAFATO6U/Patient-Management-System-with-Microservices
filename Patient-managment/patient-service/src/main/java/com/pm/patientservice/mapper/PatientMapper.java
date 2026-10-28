package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.request.PatientRequestDTO;
import com.pm.patientservice.dto.response.PatientResponseDto;
import com.pm.patientservice.model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PatientMapper {
      public PatientResponseDto toDTO(Patient patient) {
            PatientResponseDto patientDTO = new PatientResponseDto();
            patientDTO.setId(patient.getId().toString());
            patientDTO.setName(patient.getName());
            patientDTO.setAddress(patient.getAddress());
            patientDTO.setEmail(patient.getEmail());
            patientDTO.setPhone(patient.getPhone());

            patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());


            return patientDTO;
      }

      public Patient toEntity(PatientRequestDTO patientRequestDTO) {
            Patient patient = new Patient();
            patient.setName(patientRequestDTO.getName());
            patient.setAddress(patientRequestDTO.getAddress());
            patient.setEmail(patientRequestDTO.getEmail());
            patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
            patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
            patient.setPhone(patientRequestDTO.getPhone());

            return patient;
      }
}