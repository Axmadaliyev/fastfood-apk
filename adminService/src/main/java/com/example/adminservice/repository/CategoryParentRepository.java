package com.example.adminservice.repository;

import com.example.adminservice.entity.CategoryParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryParentRepository extends JpaRepository<CategoryParent,Long> {
}
