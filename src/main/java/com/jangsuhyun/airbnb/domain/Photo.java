package com.jangsuhyun.airbnb.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "file")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_id")
    private Home home; // 숙소와 다대일 관계

    @Column(nullable = false)
    private String orgFileName; // 파일 원본명

    @Column(nullable = false)
    private String filePath; // 파일 저장 경로

    private Long fileSize;

    @Builder
    public Photo(String orgFileName, String filePath, Long fileSize) {
        this.orgFileName = orgFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    // Home 정보 저장
    public void setHome(Home home) {
        this.home = home;

        // Home에 현재 파일이 존재하지 않는다면
        if (!home.getPhoto().contains(this)) {
            home.getPhoto().add(this);
        }
    }

}
