package com.app.XMLProcessing.models.DTOs.creation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "products")
public class ProductCreateRootDto {

    @XmlElement(name = "product")
    private List<ProductCreateDto> productCreateDtos;

    public List<ProductCreateDto> getProductCreateDtos() {
        return productCreateDtos;
    }

    public void setProductCreateDtos(List<ProductCreateDto> productCreateDtos) {
        this.productCreateDtos = productCreateDtos;
    }
}
