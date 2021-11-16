package com.jangsuhyun.airbnb.controller;

import com.jangsuhyun.airbnb.config.auth.LoginUser;
import com.jangsuhyun.airbnb.config.auth.dto.SessionUser;
import com.jangsuhyun.airbnb.controller.dto.BookedHomeSaveRequestDto;
import com.jangsuhyun.airbnb.domain.BookedHome;
import com.jangsuhyun.airbnb.domain.Home;
import com.jangsuhyun.airbnb.service.BookService;
import com.jangsuhyun.airbnb.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;
    private final HomeService homeService;

    // 여행 페이지로 이동
    @GetMapping("/mypage/travel")
    public String goToTravel(@LoginUser SessionUser user, Model model){

        model.addAttribute("bookedHome", bookService.findHomeByUserId(user.getId()));
        model.addAttribute("canceledHome", bookService.findCanceledHomeByUserId(user.getId()));
        // 예약 리스트를 조회할 때 이전 예약이 있는지 확인 후 있으면 status 변경
        bookService.checkPreviousHome(user.getId());
        // 이전 예약 불러오기
        model.addAttribute("previousHome", bookService.findPreviousHomeByUserId(user.getId()));

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

        bookService.addBookedHome(bookedHome);

        return "redirect:/mypage/travel";
    }

    // 숙소 예약을 취소했을 때
    @GetMapping("/book/cancel/{id}")
    public String bookCancle(@PathVariable Long id) {

        BookedHome bookedHome = bookService.findBookedHomeById(id);
        bookedHome.updateStatus(3);

        return "redirect:/mypage/travel";
    }

    // 예약 상세 페이지로 이동
    @GetMapping("/mypage/travel/{id}/{status}")
    public String goToTravelDetail(@PathVariable Long id, @PathVariable int status, Model model){

        // 로그인 한 유저와 동일한지 체크 필요

        BookedHome bookedHome = bookService.findBookedHomeById(id);

        model.addAttribute("bookedHome", bookedHome);
        model.addAttribute("status", status);

        return "mypage/travel_detail";
    }

}
