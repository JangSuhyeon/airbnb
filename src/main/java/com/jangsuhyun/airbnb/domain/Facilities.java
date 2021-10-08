package com.jangsuhyun.airbnb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

// 편의시설
@Getter
@Entity
public class Facilities {

    @Id
    private Long id;

    @Column(length = 100,nullable = false)
    private String name; //편의시설

}
