package com.challenge.longlife.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveCategoryResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String description;

    public String getName() {
        return name;
    }

    public SaveCategoryResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SaveCategoryResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
