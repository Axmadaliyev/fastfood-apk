package com.example.clientservice.controller;

import com.example.clientservice.dto.ApiResponse;
import com.example.clientservice.dto.CustomerDTO;
import com.example.clientservice.entity.Customers;
import com.example.clientservice.repository.CustomerRepository;
import com.example.clientservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomersController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;


    @GetMapping
    public ResponseEntity getAll(){
        List<Customers> all = customerRepository.findAll();
        return ResponseEntity.ok().body(all);
    }
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){

        Optional<Customers> byId = customerRepository.findById(id);
        return ResponseEntity.ok().body(byId.get());
    }
    @PostMapping
    public ResponseEntity add(@RequestBody CustomerDTO customerDTO){

        ApiResponse save = customerService.save(customerDTO);
        return ResponseEntity.status(save.isSuccess()? 201:409).body(save);
    }
    @PutMapping("/{id}")
    public ResponseEntity updete(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){

        ApiResponse updete = customerService.updete(id, customerDTO);
        return ResponseEntity.status(updete.isSuccess()? 201:409).body(updete);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        ApiResponse dalete = customerService.dalete(id);
        return ResponseEntity.ok(dalete.isSuccess()?201:409);
    }

    @GetMapping("/blocked/{id}")
    public ResponseEntity blocked(@PathVariable Long id){
        ApiResponse apiResponse = customerService.blockedClient(id);
        return ResponseEntity.ok(apiResponse.isSuccess()?201:409);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity getOneClient(@PathVariable("id") Long clientId){
        Optional<Customers> oneClient = customerRepository.getOneClient(clientId);
        if(!oneClient.isEmpty()) return ResponseEntity.ok().body(oneClient.get());

        return ResponseEntity.ok("Not found");
    }



    @GetMapping("/operator/{id}")
    public ResponseEntity getOneOperator(@PathVariable("id") Long operetorId){
        Optional<Customers> oneOperator = customerRepository.getOneOperator(operetorId);
        if(!oneOperator.isEmpty()) return ResponseEntity.ok().body(oneOperator.get());

        return ResponseEntity.ok("Not found");
    }



    @GetMapping("/curier/{id}")
    public ResponseEntity getOneCurier(@PathVariable("id") Long curierId){
        Optional<Customers> oneCurier = customerRepository.getOneCurier(curierId);
        if(!oneCurier.isEmpty()) return ResponseEntity.ok().body(oneCurier.get());

        return ResponseEntity.ok("Not found");
    }
}
