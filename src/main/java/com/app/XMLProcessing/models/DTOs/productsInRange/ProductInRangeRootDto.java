package com.app.XMLProcessing.models.DTOs.productsInRange;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "products")
public class ProductInRangeRootDto {

    @XmlElement(name = "products")
    private List<ProductInRangeDto> productInRangeDtos;

    public ProductInRangeRootDto(List<ProductInRangeDto> productsInRange) {
        this.productInRangeDtos = productsInRange;
    }

    public ProductInRangeRootDto() {

    }

    public List<ProductInRangeDto> getProductInRangeDtos() {
        return productInRangeDtos;
    }

    public void setProductInRangeDtos(List<ProductInRangeDto> productInRangeDtos) {
        this.productInRangeDtos = productInRangeDtos;
    }
}
