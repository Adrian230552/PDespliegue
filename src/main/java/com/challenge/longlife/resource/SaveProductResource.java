package com.challenge.longlife.resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class SaveProductResource {
    @NotNull
    @NotBlank
    @Size(max=75)
    private String name;

    @NotNull
    private Double price;

    @NotNull
    @NotBlank
    @Size(max=300)
    private String description;

    public String getName() {
        return name;
    }

    public SaveProductResource setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public SaveProductResource setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveProductResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
