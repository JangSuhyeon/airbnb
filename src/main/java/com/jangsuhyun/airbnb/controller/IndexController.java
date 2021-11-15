package com.jangsuhyun.airbnb.controller;

import com.jangsuhyun.airbnb.config.auth.LoginUser;
import com.jangsuhyun.airbnb.config.auth.dto.SessionUser;
import com.jangsuhyun.airbnb.controller.dto.BookedHomeSaveRequestDto;
import com.jangsuhyun.airbnb.controller.dto.HomeSaveRequestDto;
import com.jangsuhyun.airbnb.controller.dto.HomeSearchRequestDto;
import com.jangsuhyun.airbnb.domain.BookedHome;
import com.jangsuhyun.airbnb.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HomeService homeService;

    // Main
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            System.out.println(user.getName());
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

    // 여행 페이지로 이동
    @GetMapping("/mypage/travel")
    public String goToTravel(@LoginUser SessionUser user, Model model){

        model.addAttribute("bookedHome", homeService.findHomeByUserId(user.getId()));
        model.addAttribute("canceledHome", homeService.findcanceledHomeByUserId(user.getId()));

        return "mypage/travel";
    }

    // 숙소를 예약했을 때
    @PostMapping("/book/save")
    public String bookComplete(BookedHomeSaveRequestDto requestDto, @LoginUser SessionUser user)
            throws ParseException {

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        LocalDate checkIn = LocalDate.parse(requestDto.getCheckin(), formatter1); //체크인 날짜 형식 변환
        LocalDate checkOut = LocalDate.parse(requestDto.getCheckout(), formatter1); //체크아웃 날짜 형식 변환

        BookedHome bookedHome = BookedHome.builder()
                .userid(user.getId())
                .status(1)
                .home(homeService.findById(requestDto.getId()))
                .checkin(checkIn.format(formatter2))
                .checkout(checkOut.format(formatter2))
                .build();

        homeService.addBookedHome(bookedHome);

        return "redirect:/mypage/travel";
    }

    // 숙소 예약을 취소했을 때
    @GetMapping("/book/cancel/{id}")
    public String bookCancle(@PathVariable Long id) {

        BookedHome bookedHome = homeService.findBookedHomeById(id);
        bookedHome.updateStatus(3);

        return "redirect:/mypage/travel";
    }

    // 예약 상세 페이지로 이동
    @GetMapping("/mypage/travel/{id}/{status}")
    public String goToTravelDetail(@PathVariable Long id, @PathVariable int status, Model model){

        // 로그인 한 유저와 동일한지 체크 필요

        BookedHome bookedHome = homeService.findBookedHomeById(id);

        model.addAttribute("bookedHome", bookedHome);
        model.addAttribute("status", status);

        return "mypage/travel_detail";
    }

}
