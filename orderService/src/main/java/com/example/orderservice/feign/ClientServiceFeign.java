package com.example.orderservice.feign;

import com.example.orderservice.dto.ApiResponse;
import com.example.orderservice.entity.Customers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CLIENTSERVICE")
public interface ClientServiceFeign {



    @GetMapping("/client/{id}")
    ApiResponse<Customers> getOneClient(@PathVariable Long id);

    @GetMapping("/operator/{id}")
    ApiResponse<Customers> getOneOperator(@PathVariable Long id);

    @GetMapping("/curier/{id}")
    ApiResponse<Customers> getOneCurier(@PathVariable Long id);

}
