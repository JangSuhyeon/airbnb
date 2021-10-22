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

    @Column(nullable = false)
    private int price; //비용

    @Column(nullable = false)
    private String type; //숙소 유형

    // 편의시설
    @ManyToMany
    @JoinTable(name = "home_facilities", joinColumns = @JoinColumn(name = "home_id"), inverseJoinColumns = @JoinColumn(name = "facilities_id"))
    private List<Facilities> facilities = new ArrayList<>();

    // 사진
    @OneToMany(mappedBy = "home", cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
               orphanRemoval = true)
    private List<Photo> photo = new ArrayList<>();

    @Builder
    public Home(String name, String host, String address, String description, String guest, int room, int bed, int bathroom, int price, String type, List<Facilities> facilities) {
        this.name = name;
        this.host = host;
        this.address = address;
        this.description = description;
        this.guest = guest;
        this.room = room;
        this.bed = bed;
        this.bathroom = bathroom;
        this.price = price;
        this.type = type;
        this.facilities = facilities;
    }

    // 숙소 수정 시 업데이트
    public void update(String name, String host, String address, String description, String guest, int room, int bed, int bathroom, int price, String type, List<Facilities> facilities) {
        this.name = name;
        this.host = host;
        this.address = address;
        this.description = description;
        this.guest = guest;
        this.room = room;
        this.bed = bed;
        this.bathroom = bathroom;
        this.price = price;
        this.type = type;
        this.facilities = facilities;
    }

    // Home에서 파일(사진) 처리 위함
    public void addPhoto(Photo photo) {
        this.photo.add(photo);

        // Home에 파일(사진)이 저장되어있지 않은 경우
        if (photo.getHome() != this) {
            //파일(사진)저장
            photo.setHome(this);
        }
    }

}
