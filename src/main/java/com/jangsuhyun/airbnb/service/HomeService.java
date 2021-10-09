package com.jangsuhyun.airbnb.service;

import com.jangsuhyun.airbnb.controller.dto.HomeModifyRequestDto;
import com.jangsuhyun.airbnb.controller.dto.HomeSaveRequestDto;
import com.jangsuhyun.airbnb.domain.Facilities;
import com.jangsuhyun.airbnb.domain.FacilitiesRepository;
import com.jangsuhyun.airbnb.domain.Home;
import com.jangsuhyun.airbnb.domain.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HomeService {

    private final HomeRepository homeRepository;
    private final FacilitiesRepository facilitiesRepository;

    // 숙소 전체 불러오기
    public List<Home> findAll() {
        return homeRepository.findAll();
    }

    // id로 숙소 불러오기
    public Home findById(Long id) {
        return homeRepository.findById(id).get();
    }

    // 숙소 저장
    public Long save(HomeSaveRequestDto form) {
        return homeRepository.save(form.toEntity()).getId();
    }

    // 편의 시설 목록 불러오기
    public List<Facilities> findAllFacilities() {
        return facilitiesRepository.findAll();
    }

    // 숙소 삭제
    public void delete(long id) {
        homeRepository.deleteById(id);
    }

    // 숙소 수정
    public void modify(long id, HomeModifyRequestDto form) {

        // 수정할 숙소를 id로 찾기
        Home home = homeRepository.findById(id).get();

        home.update(form.getName(), form.getHost(), form.getAddress(), form.getPhoto(),
                form.getDescription(), form.getGuest(), form.getRoom(), form.getBed(),
                form.getBathroom(), form.getFacilities());
    }

}
