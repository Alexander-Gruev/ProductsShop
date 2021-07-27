package com.app.XMLProcessing.services;

import com.app.XMLProcessing.models.DTOs.categoriesByProductsCount.CategoryInfoRootDto;
import com.app.XMLProcessing.models.DTOs.creation.CategoryCreateDto;
import com.app.XMLProcessing.models.entites.Category;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {

    void seedCategories(List<CategoryCreateDto> categoryCreateDtos) throws IOException, JAXBException;

    Set<Category> getRandomCategories();

    CategoryInfoRootDto getInfoAboutAll();

    long getCategoriesCount();
}
