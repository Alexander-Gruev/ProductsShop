package com.app.XMLProcessing.services.impl;

import com.app.XMLProcessing.models.DTOs.creation.ProductCreateDto;
import com.app.XMLProcessing.models.DTOs.productsInRange.ProductInRangeDto;
import com.app.XMLProcessing.models.DTOs.productsInRange.ProductInRangeRootDto;
import com.app.XMLProcessing.models.entites.Product;
import com.app.XMLProcessing.repositories.ProductRepository;
import com.app.XMLProcessing.services.CategoryService;
import com.app.XMLProcessing.services.ProductService;
import com.app.XMLProcessing.services.UserService;
import com.app.XMLProcessing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper modelMapper, ValidationUtil validationUtil,
                              UserService userService, CategoryService categoryService) {

        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }


    @Override
    public void seedProducts(List<ProductCreateDto> productCreateDtos) {
        productCreateDtos.stream()
                .filter(this.validationUtil::isValid)
                .map(dto -> this.modelMapper.map(dto, Product.class))
                .forEach(product -> {
                    product.setBuyer(this.userService.getRandomUser());
                    product.setSeller(this.userService.getRandomUser());

                    // Products that haven't been sold yet
                    if (product.getBuyer().getId().equals(product.getSeller().getId()) ||
                            product.getPrice().compareTo(BigDecimal.valueOf(700)) > 0) {

                        product.setBuyer(null);
                    }

                    product.setCategories(this.categoryService.getRandomCategories());
                    this.productRepository.save(product);
                });
    }

    @Override
    public ProductInRangeRootDto getAllInRange(BigDecimal lowerBound, BigDecimal upperBound) {
        List<ProductInRangeDto> productsInRange = this.productRepository
                .findAllByPriceBetweenOrdered(lowerBound, upperBound)
                .stream()
                .map(product -> {
                    ProductInRangeDto dto = this.modelMapper.map(product, ProductInRangeDto.class);

                    dto.setSellerFullName(String.format("%s%s",
                            product.getSeller().getFirstName() == null ? "" : product.getSeller().getFirstName() + " ",
                            product.getSeller().getLastName()));

                    return dto;
                })
                .collect(Collectors.toList());

        return new ProductInRangeRootDto(productsInRange);
    }

    @Override
    public long getProductsCount() {
        return this.productRepository.count();
    }
}
