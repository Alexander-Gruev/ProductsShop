package com.app.XMLProcessing.services.impl;

import com.app.XMLProcessing.models.DTOs.categoriesByProductsCount.CategoryInfoRootDto;
import com.app.XMLProcessing.models.DTOs.creation.CategoryCreateDto;
import com.app.XMLProcessing.models.DTOs.categoriesByProductsCount.CategoryInfoDto;
import com.app.XMLProcessing.models.entites.Category;
import com.app.XMLProcessing.models.entites.Product;
import com.app.XMLProcessing.repositories.CategoryRepository;
import com.app.XMLProcessing.services.CategoryService;
import com.app.XMLProcessing.util.ValidationUtil;
import com.app.XMLProcessing.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final XMLParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, XMLParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedCategories(List<CategoryCreateDto> categoryCreateDtos){
        categoryCreateDtos.stream()
                .filter(this.validationUtil::isValid)
                .map(dto -> this.modelMapper.map(dto, Category.class))
                .forEach(this.categoryRepository::save);
    }

    @Override
    public Set<Category> getRandomCategories() {
        int randomCategoryCount = ThreadLocalRandom.current().nextInt(1, 4);

        Set<Category> randomCategories = new HashSet<>();

        long categoryRepoCount = this.categoryRepository.count();

        for (int i = 0; i < randomCategoryCount; i++) {
            long randomCategoryId = ThreadLocalRandom.current()
                    .nextLong(1, categoryRepoCount + 1);

            randomCategories.add(this.categoryRepository.findById(randomCategoryId).orElse(null));
        }

        return randomCategories;
    }

    @Override
    public CategoryInfoRootDto getInfoAboutAll() {
        List<CategoryInfoDto> categoryInfoDtos = this.categoryRepository.findAllByProductsCount()
                .stream()
                .map(category -> {
                    CategoryInfoDto categoryInfoDto = this.modelMapper.map(category, CategoryInfoDto.class);

                    categoryInfoDto.setName(category.getName());
                    categoryInfoDto.setProductsCount(category.getProducts().size());
                    categoryInfoDto.setAveragePrice(getAveragePriceByProductsSet(category.getProducts()));
                    categoryInfoDto.setTotalRevenue(getTotalRevenueByProductsSet(category.getProducts()));

                    return categoryInfoDto;
                })
                .collect(Collectors.toList());

        return new CategoryInfoRootDto(categoryInfoDtos);
    }

    @Override
    public long getCategoriesCount() {
        return this.categoryRepository.count();
    }

    private BigDecimal getTotalRevenueByProductsSet(Set<Product> products) {
        BigDecimal total = new BigDecimal("0");
        for (Product product : products) {
            total = total.add(product.getPrice());
        }

        return total;
    }

    private BigDecimal getAveragePriceByProductsSet(Set<Product> products) {
        BigDecimal total = getTotalRevenueByProductsSet(products);
        return total.divide(BigDecimal.valueOf(products.size()), RoundingMode.FLOOR);
    }
}
