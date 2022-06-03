package com.example.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {

//    private Long curierId;
//    private Long operatrId;

    private String payType;

    private List<OrderProductDTO> orderProductDTOS;

    private BigDecimal bigDecimal;

    private Long filialId;

    private Long clientId;
}
