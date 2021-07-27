package com.app.XMLProcessing.services;

import com.app.XMLProcessing.models.DTOs.creation.ProductCreateDto;
import com.app.XMLProcessing.models.DTOs.productsInRange.ProductInRangeRootDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void seedProducts(List<ProductCreateDto> productCreateDtos) throws IOException;

    ProductInRangeRootDto getAllInRange(BigDecimal lowerBound, BigDecimal upperBound);

    long getProductsCount();
}
