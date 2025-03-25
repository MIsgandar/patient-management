package com.gg.patientservice.service;

import com.gg.patientservice.dto.PatientRequestDTO;
import com.gg.patientservice.dto.PatientResponseDTO;
import com.gg.patientservice.exception.EmailAlreadyExistException;
import com.gg.patientservice.mapper.PatientMapper;
import com.gg.patientservice.model.Patient;
import com.gg.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOS = patients.stream()
                .map(PatientMapper::toDTO).toList();
                //.map(patient -> PatientMapper.toDTO(patient)).toList();
        return patientResponseDTOS;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistException("A patient with this email already exists" +
                    patientRequestDTO.getEmail());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }
}
