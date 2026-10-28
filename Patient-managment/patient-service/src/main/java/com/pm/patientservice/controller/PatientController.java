package com.pm.patientservice.controller;

import com.pm.patientservice.dto.request.PatientRequestDTO;
import com.pm.patientservice.dto.response.ApiResponseDTO;
import com.pm.patientservice.dto.response.PatientResponseDto;
import com.pm.patientservice.dto.validator.CreatePatientValidationGroup;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {
    private final PatientService patientService;


    @Operation(summary = "Get ALL PATIENTS ", description = "Get all patients , owned by the authenticated Admain")
    @GetMapping
    public ResponseEntity<ApiResponseDTO> getPatients(){
        List<PatientResponseDto> patientResponseDtos = patientService.getPatients();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO
                        .builder()
                        .success(true)
                        .data(patientResponseDtos)
                        .message("All Patients Returned Successfully")
                        .build()
                );

    }




    @PostMapping
    @Operation(summary = "Create a New Patient ")
    public ResponseEntity<ApiResponseDTO> createPatient (
            @Validated({Default.class, CreatePatientValidationGroup.class})
            @RequestBody PatientRequestDTO requestDTO){
        PatientResponseDto patientResponseDto = patientService.createPatient(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.builder()
                        .success(true)
                        .data(patientResponseDto)
                        .message("Patient Created Successfully")
                        .build());

    }


    @PutMapping("/{id}")
    @Operation(summary = "Update a new Patient")
    public ResponseEntity<ApiResponseDTO> updatePatient (
            @PathVariable UUID id ,
            @RequestBody PatientRequestDTO requestDTO){
        PatientResponseDto patientResponseDto = patientService.updatePatient(id,requestDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDTO.builder()
                        .success(true)
                        .data(patientResponseDto)
                        .message("Patient Updated Successfully")
                        .build());

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient")
    public ResponseEntity<Void> deletePatient (@PathVariable  UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
