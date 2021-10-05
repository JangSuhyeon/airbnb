package com.jangsuhyun.airbnb.service;

import com.jangsuhyun.airbnb.controller.dto.HomeSaveRequestDto;
import com.jangsuhyun.airbnb.domain.Facilities;
import com.jangsuhyun.airbnb.domain.FacilitiesRepository;
import com.jangsuhyun.airbnb.domain.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HomeService {

    private final HomeRepository homeRepository;
    private final FacilitiesRepository facilitiesRepository;

    // 숙소 저장
    public Long save(HomeSaveRequestDto form) {
        return homeRepository.save(form.toEntity()).getId();
    }

    // 편의 시설 목록 불러오기
    public List<Facilities> findAllFacilities() {
        return facilitiesRepository.findAll();
    }
}
