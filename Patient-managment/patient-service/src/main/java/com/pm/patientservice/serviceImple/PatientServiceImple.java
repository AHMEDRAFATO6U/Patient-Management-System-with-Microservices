package com.pm.patientservice.serviceImple;

import com.pm.patientservice.dto.request.PatientRequestDTO;
import com.pm.patientservice.dto.response.PatientResponseDto;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImple implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public List<PatientResponseDto> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patientMapper::toDTO)
                .toList();
    }


    @Override
    @Transactional
    public PatientResponseDto createPatient(PatientRequestDTO requestDTO) {

        if(patientRepository.existsByEmail (requestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email " + "already exists"
                    + requestDTO.getEmail());
        }

        Patient newPatient = patientRepository.save(patientMapper.toEntity(requestDTO));
        return patientMapper.toDTO(newPatient);
    }


    @Override
    @Transactional
    public PatientResponseDto updatePatient(UUID id, PatientRequestDTO requestDTO) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new PatientNotFoundException ("Patient not found with ID: " + id));



        if(patientRepository.existsByEmailAndIdNot(patient.getEmail() ,id)){
            throw new EmailAlreadyExistsException(
                    "A patient with this email " + "already exists"
                            + requestDTO.getEmail());
        }

        patient.setAddress(requestDTO.getAddress());
        patient.setName(requestDTO.getName());
        patient.setEmail(requestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(requestDTO.getDateOfBirth()));


        Patient updatedPatient = patientRepository.save(patient);


        return patientMapper.toDTO(updatedPatient);
    }
    @Override
    public void deletePatient(UUID id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"NO PATIENT FOUND WITH THIS ID "));

        patientRepository.delete(patient);

    }



}
