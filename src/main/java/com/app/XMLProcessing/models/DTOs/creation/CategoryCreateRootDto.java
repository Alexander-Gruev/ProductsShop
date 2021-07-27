package com.app.XMLProcessing.models.DTOs.creation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "categories")
public class CategoryCreateRootDto {

    @XmlElement(name = "category")
    private List<CategoryCreateDto> categoryCreateDtos;

    public List<CategoryCreateDto> getCategoryCreateDtos() {
        return categoryCreateDtos;
    }

    public void setCategoryCreateDtos(List<CategoryCreateDto> categoryCreateDtos) {
        this.categoryCreateDtos = categoryCreateDtos;
    }
}
