package com.github.muhammedshaheer.patientservice.exception;

public class PatientServiceException extends RuntimeException {
    public PatientServiceException() {
    }

    public PatientServiceException(String message) {
        super(message);
    }

    public PatientServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatientServiceException(Throwable cause) {
        super(cause);
    }

    public PatientServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
