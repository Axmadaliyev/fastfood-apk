package com.example.adminservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDTO {

    private String nameuz,nameru;
    private Long categoryparentId;

}
