package com.example.clientmobile.dto;

import com.example.clientmobile.entity.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private Long id;
    private Attachment photo;
    private String nameUz, nameRu;
    private BigDecimal price;
    private String description;
}
