package com.jangsuhyun.airbnb.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 숙소
@Getter
@NoArgsConstructor
@Entity
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(length = 100, nullable = false)
    private String name; //숙소명

    @Column(length = 100, nullable = false)
    private String host; //호스트명

    @Column(length = 100, nullable = false)
    private String address; //주소

    @Column(length = 100, nullable = false)
    private String photo; //사진

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description; //기본설명

    @Column(length = 100, nullable = false)
    private String guest; //예약가능한 최대 인원

    @Column(nullable = false)
    private int room; //침실 수

    @Column(nullable = false)
    private int bed; //침대 수

    @Column(nullable = false)
    private int bathroom; //욕실 수

    @OneToMany
    @JoinColumn
    private List<Facilities> facilities = new ArrayList<>(); // 편의시설

    @Builder
    public Home(String name, String host, String address, String photo, String description, String guest, int room, int bed, int bathroom) {
        this.name = name;
        this.host = host;
        this.address = address;
        this.photo = photo;
        this.description = description;
        this.guest = guest;
        this.room = room;
        this.bed = bed;
        this.bathroom = bathroom;
    }
}
