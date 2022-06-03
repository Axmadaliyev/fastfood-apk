package com.example.clientmobile.feign;

import com.example.clientmobile.entity.Customers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CLIENTSERVICE")
public interface ClientServiceFeign {

    @GetMapping("/customers")
    List<Customers> gettAllCustomers();

    @GetMapping("/customers/{id}")
    Customers gettOneCustomers(@PathVariable("id") Long costumerId);

    @GetMapping("/customers/{id}")
    List<Customers> gettMyProfile(@PathVariable Long id);
}

