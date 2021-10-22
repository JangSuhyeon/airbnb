package com.jangsuhyun.airbnb.service;

import com.jangsuhyun.airbnb.controller.FileHandler;
import com.jangsuhyun.airbnb.controller.dto.HomeModifyRequestDto;
import com.jangsuhyun.airbnb.controller.dto.HomeSaveRequestDto;
import com.jangsuhyun.airbnb.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HomeService {

    private final HomeRepository homeRepository;
    private final FacilitiesRepository facilitiesRepository;
    private final FileHandler fileHandler;
    private final PhotoRepository photoRepository;

    // 숙소 전체 불러오기
    public List<Home> findAll() {
        return homeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    // id로 숙소 불러오기
    public Home findById(Long id) {
        return homeRepository.findById(id).get();
    }

    // 숙소 저장
    @Transactional
    public Long save(HomeSaveRequestDto requestDto, List<MultipartFile> files)
         throws Exception {
        // 파일 처리를 위한 Home 객체 생성
        Home home = requestDto.toEntity();

        List<Photo> photoList = fileHandler.parseFileInfo(files);

        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()){
            for(Photo photo : photoList)
                // 파일을 DB에 저장
                home.addPhoto(photoRepository.save(photo));
        }

        return homeRepository.save(home).getId();
    }

    // 편의 시설 목록 불러오기
    public List<Facilities> findAllFacilities() {
        return facilitiesRepository.findAll();
    }

    // 숙소 삭제
    @Transactional
    public void delete(long id) {
        homeRepository.deleteById(id);
    }

    // 숙소 수정
    @Transactional
    public void modify(long id, HomeModifyRequestDto requestDto, List<MultipartFile> files) throws Exception {

        // 수정할 숙소를 id로 찾기
        Home home = homeRepository.findById(id).get(); // 수정 전 Home
        Home new_home = requestDto.toEntity();

        System.out.println("홈 서비스!!");

        List<Photo> photoList = fileHandler.parseFileInfo(files);

        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()){
            for(Photo photo : photoList)
                // 파일을 DB에 저장
                home.addPhoto(photoRepository.save(photo));
        }


        System.out.println("홈 업데이트!!");

        home.update(new_home.getName(), new_home.getHost(), new_home.getAddress(),
                new_home.getDescription(), new_home.getGuest(), new_home.getRoom(), new_home.getBed(),
                new_home.getBathroom(), new_home.getPrice(), new_home.getType(),new_home.getFacilities());
    }

}
