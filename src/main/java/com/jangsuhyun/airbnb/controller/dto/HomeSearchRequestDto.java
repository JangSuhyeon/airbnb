package com.jangsuhyun.airbnb.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

// 날짜로 예약 가능한 숙소를 검색할 때 사용
@Data
@NoArgsConstructor
public class HomeSearchRequestDto {

    private Date startday;
    private Date endday;
    private int guestCnt;

    public HomeSearchRequestDto(Date startday, Date endday, int guestCnt) {
        this.startday = startday;
        this.endday = endday;
        this.guestCnt = guestCnt;
    }
}
