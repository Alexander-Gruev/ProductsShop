package com.app.XMLProcessing.models.DTOs.creation;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductCreateDto {

    @NotNull
    @Length(min = 3)
    private String name;

    @NotNull
    private BigDecimal price;

    public ProductCreateDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
