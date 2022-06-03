package com.example.adminservice.repository;

import com.example.adminservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query(value = "select * from product where category_id=?",nativeQuery = true)
    Optional<List<Product>> getAllProductsCategoryId(Long id);


}
