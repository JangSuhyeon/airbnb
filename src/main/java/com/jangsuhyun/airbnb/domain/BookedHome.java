package com.jangsuhyun.airbnb.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 예약된 숙소
@Getter
@NoArgsConstructor
@Entity
public class BookedHome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // 예약한 사용자

    private int status; // 현재 예약 상태(예약중:1, 사용완료:2, 예약취소:3);

    @OneToOne
    @JoinColumn(name="home_id")
    private Home home; // 예약된 숙소

    @Builder
    public BookedHome(Long userId, int status, Home home) {
        this.userId = userId;
        this.status = status;
        this.home = home;
    }
}
