package com.example.clientmobile.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SECURITY")
public interface SecurityServiceFeign {
}
