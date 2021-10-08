package com.jangsuhyun.airbnb.controller.dto;

import com.jangsuhyun.airbnb.domain.Facilities;
import com.jangsuhyun.airbnb.domain.Home;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//숙소를 수정할 때 사용
@Data
@NoArgsConstructor
public class HomeModifyRequestDto {

    private String name; //숙소명
    private String host; //호스트명
    private String address; //주소
    private String photo; //사진
    private String description; //기본설명
    private String guest; //예약가능한 최대 인원
    private int room; //침실 수
    private int bed; //침대 수
    private int bathroom; //욕실 수
    private List<Facilities> facilities = new ArrayList<>(); // 편의시설
}
