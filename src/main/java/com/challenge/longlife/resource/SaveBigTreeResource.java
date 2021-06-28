package com.challenge.longlife.resource;

import com.challenge.longlife.domain.model.AuditModel;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class SaveBigTreeResource {

    @NotNull
    private String username;

    @NotNull
    private String email;

    private String firstName;

    private String lastName;

    private String gender;

    private Date bornAt;

    public String getUsername() {
        return username;
    }

    public SaveBigTreeResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveBigTreeResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public SaveBigTreeResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveBigTreeResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public SaveBigTreeResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Date getBornAt() {
        return bornAt;
    }

    public SaveBigTreeResource setBornAt(Date bornAt) {
        this.bornAt = bornAt;
        return this;
    }
}
