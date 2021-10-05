package com.jangsuhyun.airbnb.domain;

import lombok.Getter;

import javax.persistence.*;

// 편의시설
@Getter
@Entity
public class Facilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,nullable = false)
    private String name; //편의시설

}
