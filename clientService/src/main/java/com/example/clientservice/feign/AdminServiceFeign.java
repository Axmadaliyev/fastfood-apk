package com.example.clientservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@FeignClient("ADMINSERVICE")
public interface AdminServiceFeign {

    @PostMapping("/attechment/uploadMany")
    ResponseEntity UploadMany(MultipartHttpServletRequest request);



}
