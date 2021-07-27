package com.app.XMLProcessing.models.DTOs.creation;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

public class CategoryCreateDto {

    @NotNull
    @Length(min = 3, max = 15)
    private String name;

    public CategoryCreateDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
