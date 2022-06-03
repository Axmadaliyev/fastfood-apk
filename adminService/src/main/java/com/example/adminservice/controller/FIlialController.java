package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.repository.FilialRepository;
import com.example.adminservice.service.FilialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/filial")
public class FIlialController {
    private final FilialRepository filialRepository;
    private final FilialService filialService;

    @GetMapping
    public ResponseEntity getOne(@PathVariable Long id){
        Optional<Filial> byId = filialRepository.findById(id);
        return ResponseEntity.ok().body(byId.get());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Filial filial){
        ApiResponse save = filialService.save(filial);
        return ResponseEntity.status(save.isSuccess()? 201:409).body("Success");
    }

    @PutMapping("/{id}")
    public ResponseEntity updete(@PathVariable Long id,@RequestBody Filial filial){

        ApiResponse updete = filialService.updete(id, filial);
        return ResponseEntity.status(updete.isSuccess()? 201:409).body("Update");
    }

}
