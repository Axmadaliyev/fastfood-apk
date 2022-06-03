package com.example.orderservice.service;

import com.example.orderservice.dto.ApiResponse;
import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.dto.OrderProductDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderProduct;
import com.example.orderservice.entity.enums.OrderStatus;
import com.example.orderservice.entity.enums.PayType;
import com.example.orderservice.feign.AdminServiceFeign;
import com.example.orderservice.feign.ClientServiceFeign;
import com.example.orderservice.repository.OrderProductRepository;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableFeignClients
public class OrderService {

    private final AdminServiceFeign adminServiceFeign;
    private final ClientServiceFeign clientServiceFeign;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;


    public ApiResponse saveOrder(OrderDTO orderDTO) {
        Order order=new Order();


        order.setOrderStatus(OrderStatus.NEW);
        order.setFilial(adminServiceFeign.getOneFilial(orderDTO.getFilialId()).getData());
        order.setPayType(PayType.valueOf(orderDTO.getPayType()));
        order.setDeliveryPrice(orderDTO.getBigDecimal());
        order.setClient(clientServiceFeign.getOneClient(orderDTO.getClientId()).getData());
        List<OrderProduct> orderProductList=new ArrayList<>();
        for (OrderProductDTO orderProductDTO : orderDTO.getOrderProductDTOS()) {

            OrderProduct orderProduct=new OrderProduct();
            orderProduct.setCount(orderProductDTO.getCount());
            orderProduct.setProduct(adminServiceFeign.getOneProduct(orderProductDTO.getProductId()).getData());
            OrderProduct save = orderProductRepository.save(orderProduct);
            orderProductList.add(save);
        }
        order.setProducts(orderProductList);
        orderRepository.save(order);

        return new ApiResponse("saved",true,order);
    }


     public ApiResponse updete(Long id, OrderDTO orderDTO){
         Optional<Order> byId = orderRepository.findById(id);
         Order order = byId.get();
         order.setOrderStatus(OrderStatus.NEW);
         order.setDeliveryPrice(orderDTO.getBigDecimal());
         order.setPayType(PayType.valueOf(orderDTO.getPayType()));
         order.setFilial(adminServiceFeign.getOneFilial(orderDTO.getFilialId()).getData());
         order.setOperator(clientServiceFeign.getOneClient(orderDTO.getClientId()).getData());
         List<OrderProduct> orderProductList=new ArrayList<>();
         for (OrderProductDTO orderProductDTO : orderDTO.getOrderProductDTOS()) {
             Optional<OrderProduct> byId1 = orderProductRepository.findById(id);
             if (byId1.isEmpty()) return new ApiResponse("not found",false);
             OrderProduct orderProduct = byId1.get();
             orderProduct.setCount(orderProductDTO.getCount());
             orderProduct.setProduct(adminServiceFeign.getOneProduct(orderProductDTO.getProductId()).getData());
             OrderProduct save = orderProductRepository.save(orderProduct);
             orderProductList.add(save);
         }
         order.setProducts(orderProductList);
         orderRepository.save(order);
         return new ApiResponse("updete",true);
     }

     public ApiResponse ClosedOrder(Long orderId,Long clientId){
         Optional<Order> byId = orderRepository.findById(orderId);
         Order order = byId.get();
         order.setOrderStatus(OrderStatus.CLOSED);
         order.setClient(clientServiceFeign.getOneClient(clientId).getData());
         orderRepository.save(order);
         return new ApiResponse("Closed",false);
     }

     public ApiResponse SentOrder(Long orderId,Long curierId){
         Optional<Order> byId = orderRepository.findById(orderId);
         Order order = byId.get();
         order.setOrderStatus(OrderStatus.SENT);
         order.setCourier(clientServiceFeign.getOneCurier(curierId).getData());
         Order save = orderRepository.save(order);
         return new ApiResponse("Sent",true);
     }

     public ApiResponse AcceptedOrder(Long orderId,Long operatorId){

         Optional<Order> byId = orderRepository.findById(orderId);
         Order order = byId.get();
         order.setOrderStatus(OrderStatus.ACCEPTED);
         order.setOperator(clientServiceFeign.getOneOperator(operatorId).getData());
         Order save = orderRepository.save(order);
         return  new ApiResponse("accepted",true);
     }




}
