package com.example.clientmobile.feign;

import com.example.clientmobile.dto.ApiResponse;
import com.example.clientmobile.entity.Category;
import com.example.clientmobile.entity.Filial;
import com.example.clientmobile.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ADMINSERVICE")
public interface AdminServiceFeign {

    @GetMapping("/filial/{id}")
    ApiResponse<Filial> getOneFilial(@PathVariable Long id);




    @GetMapping("/product/all/{id}")
    ApiResponse<Category> getAllProductsCategoryId(@PathVariable("id") Long categoryId);



    @GetMapping("/product/{id}")
    ApiResponse<Product> getOneProduct(@PathVariable Long id);





}




