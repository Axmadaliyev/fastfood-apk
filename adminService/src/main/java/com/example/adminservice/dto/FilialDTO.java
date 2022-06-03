package com.example.adminservice.dto;

import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FilialDTO {

    private String nameUzb;
    private String nameRus;
    private String intended;
    private String address;
    private LocalTime start;
    private LocalTime finish;
    private Long latitude;
    private Long longitude;

}
