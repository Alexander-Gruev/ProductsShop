package com.app.XMLProcessing.models.DTOs.creation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserCreateRootDto {

    @XmlElement(name = "user")
    private List<UserCreateDto> userCreateDtos;

    public UserCreateRootDto() {
    }

    public List<UserCreateDto> getUserCreateDtos() {
        return userCreateDtos;
    }

    public void setUserCreateDtos(List<UserCreateDto> userCreateDtos) {
        this.userCreateDtos = userCreateDtos;
    }
}
