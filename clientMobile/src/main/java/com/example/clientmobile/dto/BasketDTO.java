package com.example.clientmobile.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BasketDTO {

    private  Long customerId;
    private List<Long> productId;
}
