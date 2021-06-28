package com.challenge.longlife.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveSupplierResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String description;

    @NotNull
    private Long number;

    public String getEmail() {
        return email;
    }

    public SaveSupplierResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveSupplierResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getNumber() {
        return number;
    }

    public SaveSupplierResource setNumber(Long number) {
        this.number = number;
        return this;
    }
}
