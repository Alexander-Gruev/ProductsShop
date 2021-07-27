package com.app.XMLProcessing.repositories;

import com.app.XMLProcessing.models.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.price > :lowerBound AND p.price < :upperBound AND p.buyer IS NULL ORDER BY p.price")
    List<Product> findAllByPriceBetweenOrdered(BigDecimal lowerBound, BigDecimal upperBound);

}
