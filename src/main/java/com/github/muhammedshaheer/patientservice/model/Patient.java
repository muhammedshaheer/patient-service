package com.github.muhammedshaheer.patientservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "patients")
public class Patient {

    @Id
    private String id;
    private String fullName;
    private String gender;
    private String phoneNumber;
    private Date createdDate;
    private Date lastModifiedDate;
    private boolean deleted;

    public Patient() {
    }

    public Patient(String fullName, String gender, String phoneNumber) {
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public Patient(String id, String fullName, String gender, String phoneNumber, Date createdDate, Date lastModifiedDate, boolean deleted) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.deleted = deleted;
    }

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
