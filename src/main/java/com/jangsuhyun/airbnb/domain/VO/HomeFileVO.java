package com.jangsuhyun.airbnb.domain.VO;

import com.jangsuhyun.airbnb.domain.Facilities;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class HomeFileVO {

    private String name; //숙소명
    private String host; //호스트명
    private String address; //주소
    private String description; //기본설명
    private String guest; //예약가능한 최대 인원
    private int room; //침실 수
    private int bed; //침대 수
    private int bathroom; //욕실 수
    private int price; //비용
    private String type; //숙소유형
    private List<Facilities> facilities = new ArrayList<>(); // 편의시설
    private List<MultipartFile> files; // 파일(사진)

}
