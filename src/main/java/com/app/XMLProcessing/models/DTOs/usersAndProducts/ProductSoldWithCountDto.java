package com.app.XMLProcessing.models.DTOs.usersAndProducts;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sold-products")
public class ProductSoldWithCountDto {

    @XmlAttribute(name = "count")
    private int count;

    @XmlElement(name = "product")
    private Set<ProductWithAttributesDto> productWithAttributesDtos;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<ProductWithAttributesDto> getProductWithAttributesDtos() {
        return productWithAttributesDtos;
    }

    public void setProductWithAttributesDtos(Set<ProductWithAttributesDto> productWithAttributesDtos) {
        this.productWithAttributesDtos = productWithAttributesDtos;
    }
}
