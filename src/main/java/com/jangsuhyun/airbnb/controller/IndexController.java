package com.jangsuhyun.airbnb.controller;

import com.jangsuhyun.airbnb.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HomeService homeService;

    // Main
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 숙소 등록 페이지로 이동
    @GetMapping("/home/add")
    public String homeAdd(Model model){

        // 편의 시설 목록 가져오기
        model.addAttribute("facilites",homeService.findAllFacilities());

        return "home/add";
    }

}
