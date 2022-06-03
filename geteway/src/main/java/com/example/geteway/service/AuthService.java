package com.example.geteway.service;


import com.example.geteway.model.AuthTokenModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "AUTH")
public interface AuthService {

    @GetMapping(value = "/auth/generate")
    AuthTokenModel getJWTToken(@RequestHeader("apikey") String apiKey);

}
