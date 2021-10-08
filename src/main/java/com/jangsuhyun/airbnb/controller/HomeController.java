package com.jangsuhyun.airbnb.controller;

import com.jangsuhyun.airbnb.controller.dto.HomeSaveRequestDto;
import com.jangsuhyun.airbnb.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class HomeController {

    private final HomeService homeService;

    // 숙소 저장
    @PostMapping("/home/save")
    public Long save(HomeSaveRequestDto form) {
        System.out.println(form.toString());
        return homeService.save(form);
    }

    // 숙소 삭제
    @PostMapping("/home/delete/{id}")
    public void delete(@PathVariable long id) {
        homeService.delete(id);
    }

    // 숙소 수정
    @PostMapping("/home/modify")
    public Long modifySave(HomeSaveRequestDto form) {
        return homeService.
    }

}
