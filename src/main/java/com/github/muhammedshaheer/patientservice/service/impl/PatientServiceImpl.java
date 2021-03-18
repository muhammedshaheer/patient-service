package com.github.muhammedshaheer.patientservice.service.impl;

import com.github.muhammedshaheer.patientservice.exception.PatientServiceException;
import com.github.muhammedshaheer.patientservice.model.Patient;
import com.github.muhammedshaheer.patientservice.repository.PatientCustomRepository;
import com.github.muhammedshaheer.patientservice.repository.PatientRepository;
import com.github.muhammedshaheer.patientservice.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientRepository;
    private final PatientCustomRepository patientCustomRepository;

    public PatientServiceImpl(PatientRepository patientRepository, PatientCustomRepository patientCustomRepository) {
        this.patientRepository = patientRepository;
        this.patientCustomRepository = patientCustomRepository;
    }

    @Override
    public void addPatientRecord(Patient patient) {
        Date date = new Date();
        patient.setCreatedDate(date);
        patient.setLastModifiedDate(date);
        patientRepository.save(patient);
        logger.info("Patient details added successfully | patientId: {}", patient.getId());
    }

    @Override
    public List<Patient> getAllPatientsRecords(String keyword, Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        List<Patient> patients = patientCustomRepository.findByPatientName(keyword, pageable);
        logger.info("Fetched patient records | keyword: {}, count: {}", keyword, patients.size());
        return patients;
    }

    @Override
    public Patient getPatientRecord(String id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        Patient patient = patientOptional.orElseThrow(() -> {
            logger.error("Invalid patientId: {}", id);
            return new PatientServiceException("Invalid patientId: " + id);
        });
        if (patient.isDeleted()) {
            logger.error("Record not found | patientId: {}", id);
            throw new PatientServiceException("Record not found | patientId: " + id);
        }
        logger.info("Fetched patient records | patientId: {}", id);
        return patient;
    }

    @Override
    public void deletePatientRecord(String id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        Patient patient = patientOptional.orElseThrow(() -> {
            logger.error("Invalid patientId: {}", id);
            return new PatientServiceException("Invalid patientId: " + id);
        });
        if (patient.isDeleted()) {
            logger.error("Record already removed | patientId: {}", id);
            throw new PatientServiceException("Record already removed | patientId: " + id);
        }
        patient.setDeleted(true);
        Date date = new Date();
        patient.setLastModifiedDate(date);
        patientRepository.save(patient);
        logger.info("Patient record removed | patientId: {}", id);
    }

    @Override
    public void updatePatientRecord(String id, Patient patientRequest) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        Patient patient = patientOptional.orElseThrow(() -> {
            logger.error("Invalid patientId: {}", id);
            return new PatientServiceException("Invalid patientId: " + id);
        });
        if (patient.isDeleted()) {
            logger.error("Record not found | patientId: {}", id);
            throw new PatientServiceException("Record not found | patientId: " + id);
        }
        patient.setFullName(patientRequest.getFullName());
        patient.setGender(patientRequest.getGender());
        patient.setPhoneNumber(patientRequest.getPhoneNumber());
        Date date = new Date();
        patient.setLastModifiedDate(date);
        patientRepository.save(patient);
        logger.info("Patient record updated | patientId: {}", id);
    }
}
