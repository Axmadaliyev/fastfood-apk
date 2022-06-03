package com.example.adminservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name,contentType;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private byte[] bytes;


}
