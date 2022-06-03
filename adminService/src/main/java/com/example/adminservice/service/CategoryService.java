package com.example.adminservice.service;

import com.example.adminservice.dto.CategoryDTO;
import com.example.adminservice.entity.Category;
import com.example.adminservice.entity.CategoryParent;
import com.example.adminservice.repository.CategoryParentRepository;
import com.example.adminservice.repository.CategoryRepository;
import com.example.orderservice.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {



    private  final CategoryRepository categoryRepository;

    private final CategoryParentRepository categoryParentRepository;



    public ApiResponse saveCategoryParent(CategoryParent categoryParent){
        CategoryParent save = categoryParentRepository.save(categoryParent);
        return new ApiResponse("save",true);
    }


    public ApiResponse saveCategory(CategoryDTO categoryDTO){
        Category category=new Category();
        category.setNameuz(categoryDTO.getNameuz());
        category.setNameru(categoryDTO.getNameru());
        category.setParent(categoryParentRepository.getById(categoryDTO.getCategoryparentId()));
        Category save = categoryRepository.save(category);
        return new ApiResponse("save",true);
    }

    public ApiResponse updeteCategoryParent(Long id,CategoryParent categoryParent){
        Optional<CategoryParent> byId = categoryParentRepository.findById(id);
        CategoryParent categoryParent1 = byId.get();
        categoryParent1.setMaincategory(categoryParent.getMaincategory());
        CategoryParent save = categoryParentRepository.save(categoryParent1);
        return new ApiResponse("updete",true);
    }
    public ApiResponse updeteCategory(Long id,CategoryDTO categoryDTO){

        Optional<Category> byId = categoryRepository.findById(id);
        Category category = byId.get();
        category.setNameuz(categoryDTO.getNameuz());
        category.setNameru(categoryDTO.getNameru());
        category.setParent(categoryParentRepository.getById(categoryDTO.getCategoryparentId()));
        Category save = categoryRepository.save(category);
        return new ApiResponse("updete",true);
    }

    public ApiResponse deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return new ApiResponse("delete",true);
    }
    public ApiResponse daleteCategoryParent(Long id){
        categoryParentRepository.deleteById(id);
        return new ApiResponse("delete",true);
    }

}
