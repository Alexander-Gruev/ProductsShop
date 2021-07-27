package com.app.XMLProcessing.models.DTOs.usersAndProducts;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
public class UserInfoDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute(name = "age")
    private int age;

    @XmlElement(name = "sold-products")
    private ProductSoldWithCountDto productSoldWithCountDto;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProductSoldWithCountDto getProductSoldWithCountDto() {
        return productSoldWithCountDto;
    }

    public void setProductSoldWithCountDto(ProductSoldWithCountDto productSoldWithCountDto) {
        this.productSoldWithCountDto = productSoldWithCountDto;
    }
}
