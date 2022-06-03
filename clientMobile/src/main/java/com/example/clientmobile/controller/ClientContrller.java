package com.example.clientmobile.controller;

import com.example.clientmobile.dto.ApiResponse;
import com.example.clientmobile.dto.BasketDTO;
import com.example.clientmobile.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientcontroller")
@RequiredArgsConstructor
public class ClientContrller {

    private final BasketService basketService;



    //TODO  selectLanguage
    //TODO  register
    //TODO  login
    //TODO  getProducts By CategoryId
    //TODO  addProduct to Basket
    //TODO  getMy Products from Basket
    //TODO  getFilial ByCity
    //TODO  addOrder from Basket
    //TODO  deleteMy Products from Basket
    //TODO  getMyProfile
    //TODO  getMyAllOrders
    //TODO  getOrderInformation

    @PostMapping("/basket")
    public ResponseEntity saveBasket(@RequestBody BasketDTO basketDTO){
        ApiResponse save = basketService.save(basketDTO);
        return ResponseEntity.status(save.isSuccess()? 201:409).body("saved");
    }


}