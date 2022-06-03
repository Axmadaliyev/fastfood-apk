package com.example.clientmobile.service;

import com.example.clientmobile.dto.ApiResponse;
import com.example.clientmobile.dto.BasketDTO;
import com.example.clientmobile.entity.Basket;
import com.example.clientmobile.entity.Product;
import com.example.clientmobile.feign.AdminServiceFeign;
import com.example.clientmobile.feign.ClientServiceFeign;
import com.example.clientmobile.repository.BasketRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {

    @Autowired
    private BasketRepositroy basketRepositroy;
    @Autowired(required = false)
    private AdminServiceFeign adminServiceFeign;
    @Autowired(required = false)
    private ClientServiceFeign clientServiceFeign;

    public ApiResponse save(BasketDTO basketDTO){
        Basket basket=new Basket();
        List<Product> products=new ArrayList<>();
        basket.setCustomers(clientServiceFeign.gettOneCustomers(basketDTO.getCustomerId()));
        for (Long aLong : basketDTO.getProductId()) {
            products.add(adminServiceFeign.getOneProduct(aLong).getData());
        }
        basket.setProducts(products);
        Basket save = basketRepositroy.save(basket);
        return new ApiResponse("save",true);
    }



}
