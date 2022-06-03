package com.example.orderservice.controller;

import com.example.orderservice.dto.ApiResponse;
import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity saveOrder(@RequestBody OrderDTO orderDTO){
        ApiResponse apiResponse = orderService.saveOrder(orderDTO);
        return ResponseEntity.status(apiResponse.isSuccess()? 201:409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneOrder(@PathVariable Long id){

        Optional<Order> byId = orderRepository.findById(id);
        if (!byId.isEmpty()) {
            return ResponseEntity.ok().body(byId.get());
        }else {
            return ResponseEntity.ok("Topilmadi");
        }
    }

    @GetMapping
    public ResponseEntity getAllOrder(){
        List<Order> all = orderRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @PutMapping("/{id}")
    public ResponseEntity updete(@PathVariable Long id,@RequestBody OrderDTO orderDTO){

        ApiResponse updete = orderService.updete(id, orderDTO);

        return ResponseEntity.status(updete.isSuccess()? 201:409).body(updete);

    }

    @GetMapping("/cancel")
    public ResponseEntity ClosedOrder(@Param("orderId") Long orderId,@Param("clientId") Long clientID){
        ApiResponse apiResponse = orderService.ClosedOrder(orderId, clientID);
        return ResponseEntity.status(apiResponse.isSuccess()? 201:409).body("success!");
    }



    @GetMapping("/sent")
    public ResponseEntity SentOrder(@Param("orderId") Long orderId,@Param("Id") Long curierID){
        ApiResponse apiResponse = orderService.SentOrder(orderId, curierID);
        return ResponseEntity.status(apiResponse.isSuccess()? 201:409).body("success!");
    }


    @GetMapping("/accepted")
    public ResponseEntity AcceptedOrder(@Param("orderId") Long orderId,@Param("operatorId") Long operatorID){
        ApiResponse apiResponse = orderService.AcceptedOrder(orderId, operatorID);
        return ResponseEntity.status(apiResponse.isSuccess()? 201:409).body("success!");
    }

    @GetMapping("/client/{id}")
    public ResponseEntity getMyAllOrders(@PathVariable Long id){
        List<Order> allByClientId = orderRepository.getAllByClientId(id);
        return ResponseEntity.ok().body(allByClientId);
    }




}
