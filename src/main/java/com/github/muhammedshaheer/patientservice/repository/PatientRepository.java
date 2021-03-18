package com.github.muhammedshaheer.patientservice.repository;

import com.github.muhammedshaheer.patientservice.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
}
