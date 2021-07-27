package com.app.XMLProcessing.repositories;

import com.app.XMLProcessing.models.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u FROM User u JOIN Product p ON u.id = p.seller.id " +
            "WHERE p.buyer IS NOT NULL ORDER BY u.lastName, u.firstName")
    List<User> findAllThatHaveAtLeastOneProductSoldOrderedByNames();

    @Query("SELECT DISTINCT u FROM User u JOIN Product p ON u.id = p.seller.id " +
            "WHERE p.buyer IS NOT NULL")
    List<User> findAllThatHaveAtLeastOneProductSoldOrderedByProductsSoldCount();

}
