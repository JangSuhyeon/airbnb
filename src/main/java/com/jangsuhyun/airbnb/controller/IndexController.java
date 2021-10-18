package com.jangsuhyun.airbnb.controller;

import com.jangsuhyun.airbnb.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HomeService homeService;

    // Main
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 숙소 목록
    @GetMapping("/list")
    public String list(Model model) {

        // 숙소 불러오기
        model.addAttribute("homes",homeService.findAll());

        return "list";
    }

    // 숙소 등록 페이지로 이동
    @GetMapping("/home/add")
    public String homeAdd(Model model){

        // 편의 시설 목록 가져오기
        model.addAttribute("facilites",homeService.findAllFacilities());

        return "home/add";
    }

    // 숙소 수정 페이지로 이동
    @GetMapping("/home/edit/{id}")
    public String homeDelete(@PathVariable long id, Model model) {

        // 수정할 숙소 불러오기
        model.addAttribute("home", homeService.findById(id));

        // 편의 시설 목록 가져오기
        model.addAttribute("facilites",homeService.findAllFacilities());

        return "home/edit";
    }

}
