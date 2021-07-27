package com.app.XMLProcessing.models.DTOs.soldProducts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithProductsSoldRootDto {

    @XmlElement(name = "user")
    private List<UserWithProductsSoldDto> userWithProductsSoldDtos;

    public UserWithProductsSoldRootDto(List<UserWithProductsSoldDto> userWithProductsSoldDtos) {
        this.userWithProductsSoldDtos = userWithProductsSoldDtos;
    }

    public UserWithProductsSoldRootDto() {

    }

    public List<UserWithProductsSoldDto> getUserWithProductsSoldDtos() {
        return userWithProductsSoldDtos;
    }

    public void setUserWithProductsSoldDtos(List<UserWithProductsSoldDto> userWithProductsSoldDtos) {
        this.userWithProductsSoldDtos = userWithProductsSoldDtos;
    }
}
