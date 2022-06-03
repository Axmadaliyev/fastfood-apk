package com.example.clientmobile.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CustomerDTO {

    private String name;
    private String phonenumber;
    private String clientsatus;
    private LocalDate birthdate;
    private String region;
    private String language;
    private String userType;
    private Long photoId;
}
