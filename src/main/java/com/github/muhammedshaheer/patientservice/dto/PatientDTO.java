package com.github.muhammedshaheer.patientservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PatientDTO {

    private String id;

    @NotBlank(message = "please provide the fullName")
    @Size(max = 200, message = "Reward name must be less than 200 characters")
    private String fullName;

    private String gender;

    @NotBlank(message = "Please provide the phoneNumber")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String phoneNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
