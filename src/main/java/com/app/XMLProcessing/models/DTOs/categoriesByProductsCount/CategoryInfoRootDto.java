package com.app.XMLProcessing.models.DTOs.categoriesByProductsCount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "categories")
public class CategoryInfoRootDto {

    @XmlElement(name = "category")
    private List<CategoryInfoDto> categoryInfoDtos;

    public CategoryInfoRootDto(List<CategoryInfoDto> categoryInfoDtos) {
        this.categoryInfoDtos = categoryInfoDtos;
    }

    public CategoryInfoRootDto() {

    }

    public List<CategoryInfoDto> getCategoryInfoDtos() {
        return categoryInfoDtos;
    }

    public void setCategoryInfoDtos(List<CategoryInfoDto> categoryInfoDtos) {
        this.categoryInfoDtos = categoryInfoDtos;
    }
}
