package com.example.clientmobile.feign;

import com.example.clientmobile.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ORDER")
public interface OrderServiceFeign {

    @GetMapping("/order/client/{id}")
    List<Order>  getMyAllOrders(@PathVariable("id") Long costumerId);



    @GetMapping("/order")
    List<Order>  getAllOrders();
    
}
