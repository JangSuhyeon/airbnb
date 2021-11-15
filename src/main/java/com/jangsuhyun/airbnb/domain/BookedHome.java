package com.jangsuhyun.airbnb.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

// 예약된 숙소
@Getter
@NoArgsConstructor
@Entity
public class BookedHome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userid; // 예약한 사용자

    private int status; // 현재 예약 상태(예약중:1, 사용완료:2, 예약취소:3);

    private String checkin; // 체크인 예약 날짜
    private String checkout; // 체크아웃 예약 날짜

    @OneToOne
    @JoinColumn(name="home_id")
    private Home home; // 예약된 숙소

    @Builder
    public BookedHome(Long userid, int status, String checkin, String checkout, Home home) {
        this.userid = userid;
        this.status = status;
        this.checkin = checkin;
        this.checkout = checkout;
        this.home = home;
    }

    public void updateStatus(int status) {
        this.status = status;
    }
}
