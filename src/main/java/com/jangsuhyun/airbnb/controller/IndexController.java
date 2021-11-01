package com.jangsuhyun.airbnb.controller;

import com.jangsuhyun.airbnb.config.auth.LoginUser;
import com.jangsuhyun.airbnb.config.auth.dto.SessionUser;
import com.jangsuhyun.airbnb.controller.dto.HomeSaveRequestDto;
import com.jangsuhyun.airbnb.controller.dto.HomeSearchRequestDto;
import com.jangsuhyun.airbnb.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HomeService homeService;
    private final HttpSession httpSession;

    // Main
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("userName",user.getName());
        }

        return "index";
    }

    // 날짜로 숙소 검색
    @GetMapping("/home/search")
    public String search(HttpServletRequest request, Model model) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // String -> date 위함
        Date startday = new Date(dateFormat.parse(request.getParameter("startday")).getTime());
        Date endday = new Date(dateFormat.parse(request.getParameter("endday")).getTime());
        int guestCnt = Integer.parseInt(request.getParameter("guestCnt"));
        HomeSearchRequestDto requestDto = new HomeSearchRequestDto(startday, endday, guestCnt);
        model.addAttribute("homes", homeService.findByDate(requestDto));

        return "home/list";
    }

    // 숙소 목록
    @GetMapping("/home/list")
    public String list(Model model) {

        // 숙소 불러오기
        model.addAttribute("homes",homeService.findAll());

        return "home/list";
    }

    // 숙소 상세 페이지
    @GetMapping("/home/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {

        model.addAttribute("home",homeService.findById(id));

        return "home/detail";
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
