package com.jangsuhyun.airbnb.controller;

import com.jangsuhyun.airbnb.controller.dto.HomeModifyRequestDto;
import com.jangsuhyun.airbnb.controller.dto.HomeSaveRequestDto;
import com.jangsuhyun.airbnb.domain.VO.HomeFileVO;
import com.jangsuhyun.airbnb.service.HomeService;
import com.jangsuhyun.airbnb.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final HomeService homeService;

    // 숙소 저장
    @PostMapping("/home/save")
    public String save(HomeFileVO homeFileVO) throws Exception {
        System.out.println("저장 완료 : " + homeFileVO.toString());

        HomeSaveRequestDto requestDto = HomeSaveRequestDto.builder()
                .name(homeFileVO.getName())
                .host(homeFileVO.getHost())
                .address(homeFileVO.getAddress())
                .description(homeFileVO.getDescription())
                .guest(homeFileVO.getGuest())
                .room(homeFileVO.getRoom())
                .bed(homeFileVO.getBed())
                .bathroom(homeFileVO.getBathroom())
                .facilities(homeFileVO.getFacilities())
                .build();

        homeService.save(requestDto, homeFileVO.getFiles());
        return "redirect:/";
    }

    // 숙소 삭제
    @PostMapping("/home/delete/{id}")
    public String delete(@PathVariable long id) {
        homeService.delete(id);
        return "redirect:/";
    }

    // 숙소 수정
    @Transactional
    @PostMapping ("/home/modify/{id}")
    public String modifySave(@PathVariable long id, HomeModifyRequestDto form) {
        homeService.modify(id,form);
        System.out.println("수정 완료 : " + form.toString());
        return "redirect:/";
    }

}
