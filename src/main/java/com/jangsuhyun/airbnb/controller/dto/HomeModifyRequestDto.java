package com.jangsuhyun.airbnb.controller.dto;

import com.jangsuhyun.airbnb.domain.Facilities;
import com.jangsuhyun.airbnb.domain.Home;
import com.jangsuhyun.airbnb.domain.Photo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//숙소를 수정할 때 사용
@Data
@NoArgsConstructor
public class HomeModifyRequestDto {

    private String name; //숙소명
    private String host; //호스트명
    private String address; //주소
    private String description; //기본설명
    private int guest; //예약가능한 최대 인원
    private int room; //침실 수
    private int bed; //침대 수
    private int bathroom; //욕실 수
    private int price; //비용
    private String type; //숙소유형
    private Date startDay; //예약 가능한 시작 날짜
    private Date endDay; //예약 가능한 마지막 날짜
    private List<Facilities> facilities = new ArrayList<>(); // 편의시설

    @Builder
    public HomeModifyRequestDto(String name, String host, String address, String description, int guest, int room, int bed, int bathroom, int price, String type, Date startDay, Date endDay, List<Facilities> facilities) {
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
        this.startDay = startDay;
        this.endDay = endDay;
        this.facilities = facilities;
    }

    public Home toEntity() {
        return Home.builder()
                .name(name)
                .host(host)
                .address(address)
                .description(description)
                .guest(guest)
                .room(room)
                .bed(bed)
                .bathroom(bathroom)
                .price(price)
                .type(type)
                .startDay(startDay)
                .endDay(endDay)
                .facilities(facilities)
                .build();
    }
}
