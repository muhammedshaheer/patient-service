package com.github.muhammedshaheer.patientservice.service;

import com.github.muhammedshaheer.patientservice.exception.PatientServiceException;
import com.github.muhammedshaheer.patientservice.model.Patient;
import com.github.muhammedshaheer.patientservice.repository.PatientCustomRepository;
import com.github.muhammedshaheer.patientservice.repository.PatientRepository;
import com.github.muhammedshaheer.patientservice.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientCustomRepository patientCustomRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    public void test_AddRecord() {
        Patient patient = new Patient();
        patient.setFullName("Full Name");
        patient.setGender("Male");
        patient.setPhoneNumber("+91-9847688523");
        patientService.addPatientRecord(patient);
    }

    @Test
    public void test_GetPatientRecord_NotFound() {
        String id = "6052e157a6f7911be6111c80";
        //Mocking of repository call to return null
        Mockito.when(patientRepository.findById(id))
                .thenReturn(Optional.empty());
        //Throws an exception because record is null
        assertThrows(PatientServiceException.class, () -> patientService.getPatientRecord(id));
    }

    @Test
    public void test_GetPatientRecord_Deleted() {
        String id = "6052e157a6f7911be6111c80";
        //Mocking of repository call to return a record with deleted as true
        Mockito.when(patientRepository.findById(id))
                .thenReturn(java.util.Optional.of(new Patient("6052e157a6f7911be6111c80", "Muhammed Shaheer", "Male", "+91-9847688523", new Date(), new Date(), true)));
        //Throws an exception because record has deleted as true
        assertThrows(PatientServiceException.class, () -> patientService.getPatientRecord(id));
    }

    @Test
    public void test_DeletePatientRecord_NotFound() {
        String id = "6052e157a6f7911be6111c80";
        //Mocking of repository call to return null
        Mockito.when(patientRepository.findById(id))
                .thenReturn(Optional.empty());
        //Throws an exception because record is null
        assertThrows(PatientServiceException.class, () -> patientService.deletePatientRecord(id));
    }

    @Test
    public void test_DeletePatientRecord_AlreadyDeleted() {
        String id = "6052e157a6f7911be6111c80";
        //Mocking of repository call to return a record with deleted as true
        Mockito.when(patientRepository.findById(id))
                .thenReturn(java.util.Optional.of(new Patient("6052e157a6f7911be6111c80", "Muhammed Shaheer", "Male", "+91-9847688523", new Date(), new Date(), true)));
        //Throws an exception because record has deleted as true
        assertThrows(PatientServiceException.class, () -> patientService.deletePatientRecord(id));
    }

    @Test
    public void test_UpdatePatientRecord_NotFound() {
        String id = "6052e157a6f7911be6111c80";
        //Mocking of repository call to return null
        Mockito.when(patientRepository.findById(id))
                .thenReturn(Optional.empty());
        //Mock request
        Patient patientRequest = new Patient();
        patientRequest.setFullName("Full Name");
        patientRequest.setGender("Male");
        patientRequest.setPhoneNumber("+91-9847688523");
        //Throws an exception because record is null
        assertThrows(PatientServiceException.class, () -> patientService.updatePatientRecord(id, patientRequest));
    }

    @Test
    public void test_UpdatePatientRecord_AlreadyDeleted() {
        String id = "6052e157a6f7911be6111c80";
        //Mocking of repository call to return a record with deleted as true
        Mockito.when(patientRepository.findById(id))
                .thenReturn(java.util.Optional.of(new Patient("6052e157a6f7911be6111c80", "Muhammed Shaheer", "Male", "+91-9847688523", new Date(), new Date(), true)));
        //Mock request
        Patient patientRequest = new Patient();
        patientRequest.setFullName("Full Name");
        patientRequest.setGender("Male");
        patientRequest.setPhoneNumber("+91-9847688523");
        //Throws an exception because record has deleted as true
        assertThrows(PatientServiceException.class, () -> patientService.updatePatientRecord(id, patientRequest));
    }
}
