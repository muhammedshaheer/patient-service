package com.github.muhammedshaheer.patientservice.service;

import com.github.muhammedshaheer.patientservice.model.Patient;

import java.util.List;

public interface PatientService {
    void addPatientRecord(Patient patient);

    List<Patient> getAllPatientsRecords(String keyword, Integer offset, Integer limit);

    Patient getPatientRecord(String id);

    void deletePatientRecord(String id);

    void updatePatientRecord(String id, Patient patient);
}
