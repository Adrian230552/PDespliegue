package com.challenge.longlife.resource;

import com.challenge.longlife.domain.model.AuditModel;

import java.util.Date;

public class BigTreeResource{

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private Date bornAt;

    public Long getId() {
        return id;
    }

    public BigTreeResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public BigTreeResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public BigTreeResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public BigTreeResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BigTreeResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public BigTreeResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Date getBornAt() {
        return bornAt;
    }

    public BigTreeResource setBornAt(Date bornAt) {
        this.bornAt = bornAt;
        return this;
    }

}
