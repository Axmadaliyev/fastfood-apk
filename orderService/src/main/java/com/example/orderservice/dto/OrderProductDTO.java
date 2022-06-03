package com.example.orderservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderProductDTO {

    private Long productId;

    private Integer count;

}
