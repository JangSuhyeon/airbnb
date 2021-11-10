package com.jangsuhyun.airbnb.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 숙소 예약할 때 사용
@Data
@NoArgsConstructor
public class BookedHomeSaveRequestDto {

    private Long id; // Home Id
    private String checkin; // 체크인 예약 날짜
    private String checkout; // 체크아웃 예약 날짜

    @Builder
    public BookedHomeSaveRequestDto(Long id, String checkin, String checkout) {
        this.id = id;
        this.checkin = checkin;
        this.checkout = checkout;
    }
}
