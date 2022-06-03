package com.example.adminservice.controller;

import com.example.adminservice.dto.CategoryDTO;
import com.example.adminservice.entity.Category;
import com.example.adminservice.entity.CategoryParent;
import com.example.adminservice.repository.CategoryParentRepository;
import com.example.adminservice.repository.CategoryRepository;
import com.example.adminservice.service.CategoryService;
import com.example.orderservice.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryParentRepository categoryParentRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity getallCategory(){
        List<Category> all = categoryRepository.findAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneCategory(@PathVariable Long id){
        Optional<Category> byId = categoryRepository.findById(id);
        return ResponseEntity.ok().body(byId.get());
    }

    @GetMapping("/parent")
    public ResponseEntity getAllParentCategory(){
        List<CategoryParent> all = categoryParentRepository.findAll();
        return ResponseEntity.ok().body(all);
    }
    @PostMapping
    public ResponseEntity saveCateory(@RequestBody CategoryDTO categoryDTO){
        ApiResponse apiResponse = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.status(apiResponse.isSuccess()? 201:409).body(apiResponse);
    }
    @PostMapping("/parent")
    public ResponseEntity saveParentCategory(CategoryParent categoryParent){
        ApiResponse apiResponse = categoryService.saveCategoryParent(categoryParent);
        return ResponseEntity.status(apiResponse.isSuccess()? 201:409).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(Long id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 201:409).body("delete");
    }

    @PutMapping("/{id}")
    public ResponseEntity updeteCategory(@PathVariable Long id,@RequestBody CategoryDTO categoryDTO){
        ApiResponse apiResponse = categoryService.updeteCategory(id, categoryDTO);
        return ResponseEntity.status(apiResponse.isSuccess()? 201:409).body("updete");
    }


}
