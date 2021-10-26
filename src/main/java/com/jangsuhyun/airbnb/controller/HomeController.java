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
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final HomeService homeService;

    // 숙소 저장
    @PostMapping("/home/save")
    public String save(HomeFileVO homeFileVO) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // String -> date 위함
        System.out.println(homeFileVO.getStartDay());

        HomeSaveRequestDto requestDto = HomeSaveRequestDto.builder()
                .name(homeFileVO.getName())
                .host(homeFileVO.getHost())
                .address(homeFileVO.getAddress())
                .description(homeFileVO.getDescription())
                .guest(homeFileVO.getGuest())
                .room(homeFileVO.getRoom())
                .bed(homeFileVO.getBed())
                .bathroom(homeFileVO.getBathroom())
                .price(homeFileVO.getPrice())
                .type(homeFileVO.getType())
                .startDay(new Date(dateFormat.parse(homeFileVO.getStartDay()).getTime()))
                .endDay(new Date(dateFormat.parse(homeFileVO.getEndDay()).getTime()))
                .facilities(homeFileVO.getFacilities())
                .build();

        Long id = homeService.save(requestDto, homeFileVO.getFiles());
        return "redirect:/home/detail/" + id;
    }

    // 숙소 삭제
    @GetMapping("/home/delete/{id}")
    public String delete(@PathVariable long id) {
        homeService.delete(id);
        return "redirect:/home/list";
    }

    // 숙소 수정
    @Transactional
    @PostMapping ("/home/modify/{id}")
    public String modifySave(@PathVariable long id, HomeFileVO homeFileVO) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // String -> date 위함

        HomeModifyRequestDto requestDto = HomeModifyRequestDto.builder()
                .name(homeFileVO.getName())
                .host(homeFileVO.getHost())
                .address(homeFileVO.getAddress())
                .description(homeFileVO.getDescription())
                .guest(homeFileVO.getGuest())
                .room(homeFileVO.getRoom())
                .bed(homeFileVO.getBed())
                .bathroom(homeFileVO.getBathroom())
                .price(homeFileVO.getPrice())
                .type(homeFileVO.getType())
                .startDay(new Date(dateFormat.parse(homeFileVO.getStartDay()).getTime()))
                .endDay(new Date(dateFormat.parse(homeFileVO.getEndDay()).getTime()))
                .facilities(homeFileVO.getFacilities())
                .build();
        System.out.println("홈 컨트롤러!!");

        homeService.modify(id, requestDto, homeFileVO.getFiles());
        return "redirect:/home/detail/" + id;
    }

}
