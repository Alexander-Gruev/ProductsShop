package com.app.XMLProcessing.models.DTOs.usersAndProducts;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithCountDto {

    @XmlAttribute(name = "count")
    private int count;

    @XmlElement(name = "user")
    private List<UserInfoDto> userInfoDtos;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserInfoDto> getUserInfoDtos() {
        return userInfoDtos;
    }

    public void setUserInfoDtos(List<UserInfoDto> userInfoDtos) {
        this.userInfoDtos = userInfoDtos;
    }
}
