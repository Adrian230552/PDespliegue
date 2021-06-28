package com.challenge.longlife.resource;

public class SupplierResource {
    private Long id;
    private String email;
    private String description;
    private Long number;

    public Long getId() {
        return id;
    }

    public SupplierResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SupplierResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SupplierResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getNumber() {
        return number;
    }

    public SupplierResource setNumber(Long number) {
        this.number = number;
        return this;
    }
}
