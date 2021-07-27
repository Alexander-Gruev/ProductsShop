package com.app.XMLProcessing.services.impl;

import com.app.XMLProcessing.models.DTOs.creation.UserCreateDto;
import com.app.XMLProcessing.models.DTOs.soldProducts.UserWithProductsSoldDto;
import com.app.XMLProcessing.models.DTOs.soldProducts.UserWithProductsSoldRootDto;
import com.app.XMLProcessing.models.DTOs.usersAndProducts.ProductSoldWithCountDto;
import com.app.XMLProcessing.models.DTOs.usersAndProducts.ProductWithAttributesDto;
import com.app.XMLProcessing.models.DTOs.usersAndProducts.UserInfoDto;
import com.app.XMLProcessing.models.DTOs.usersAndProducts.UserWithCountDto;
import com.app.XMLProcessing.models.entites.User;
import com.app.XMLProcessing.repositories.UserRepository;
import com.app.XMLProcessing.services.UserService;
import com.app.XMLProcessing.util.ValidationUtil;
import com.app.XMLProcessing.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final XMLParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, XMLParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUsers(List<UserCreateDto> userCreateDtos){
        userCreateDtos.stream()
                .filter(this.validationUtil::isValid)
                .map(dto -> this.modelMapper.map(dto, User.class))
                .forEach(this.userRepository::save);
    }

    @Override
    public User getRandomUser() {
        long randomUserId = ThreadLocalRandom.current()
                .nextLong(1, this.userRepository.count() + 1);

        return this.userRepository.findById(randomUserId).orElse(null);
    }

    @Override
    public UserWithProductsSoldRootDto getAllWithProductSold() {

        List<UserWithProductsSoldDto> userWithProductsSoldDtos = this.userRepository
                .findAllThatHaveAtLeastOneProductSoldOrderedByNames()
                .stream()
                .filter(user -> user.getSoldProducts().removeIf(p -> p.getBuyer() == null))
                .map(user -> this.modelMapper.map(user, UserWithProductsSoldDto.class))
                .collect(Collectors.toList());

        return new UserWithProductsSoldRootDto(userWithProductsSoldDtos);
    }

    @Override
    public long getUsersCount() {
        return this.userRepository.count();
    }

    @Override
    public UserWithCountDto getAllSellersOrderedByProductsSold() {
        List<UserInfoDto> userInfoDtos = this.userRepository.findAllThatHaveAtLeastOneProductSoldOrderedByProductsSoldCount()
                .stream()
                .map(this::mapFromUserToUserInfoDto)
                .sorted((a, b) -> {
                    int result = b.getProductSoldWithCountDto().getCount() - a.getProductSoldWithCountDto().getCount();
                    if (result == 0) {
                        return a.getLastName().compareTo(b.getLastName());
                    }
                    return result;
                })
                .collect(Collectors.toList());

        UserWithCountDto userWithCountDto = new UserWithCountDto();
        userWithCountDto.setUserInfoDtos(userInfoDtos);
        userWithCountDto.setCount(userInfoDtos.size());

        return userWithCountDto;
    }

    public UserInfoDto mapFromUserToUserInfoDto(User user) {
        UserInfoDto userInfoDto = this.modelMapper.map(user, UserInfoDto.class);
        ProductSoldWithCountDto productSoldWithCountDto = new ProductSoldWithCountDto();
        productSoldWithCountDto.setCount(user.getSoldProducts().size());

        productSoldWithCountDto.setProductWithAttributesDtos(
                user
                        .getSoldProducts()
                        .stream()
                        .map(product -> this.modelMapper.map(product, ProductWithAttributesDto.class))
                        .collect(Collectors.toSet()));

        userInfoDto.setProductSoldWithCountDto(productSoldWithCountDto);
        return userInfoDto;
    }
}
