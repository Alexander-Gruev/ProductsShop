package com.app.XMLProcessing.services;

import com.app.XMLProcessing.models.DTOs.creation.UserCreateDto;
import com.app.XMLProcessing.models.DTOs.soldProducts.UserWithProductsSoldRootDto;
import com.app.XMLProcessing.models.DTOs.usersAndProducts.UserWithCountDto;
import com.app.XMLProcessing.models.entites.User;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    void seedUsers(List<UserCreateDto> userCreateDtos) throws IOException, JAXBException;

    User getRandomUser();

    UserWithProductsSoldRootDto getAllWithProductSold();

    long getUsersCount();

    UserWithCountDto getAllSellersOrderedByProductsSold();
}
