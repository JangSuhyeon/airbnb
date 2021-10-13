package com.jangsuhyun.airbnb.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class PhotoDto {

    private String origFileName; // 파일 원본명
    private String filePath; // 파일 저장 경로
    private Long fileSize;

    @Builder
    public PhotoDto(String origFileName, String filePath, Long fileSize) {
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
