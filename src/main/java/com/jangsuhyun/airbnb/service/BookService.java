package com.jangsuhyun.airbnb.service;

import com.jangsuhyun.airbnb.config.auth.LoginUser;
import com.jangsuhyun.airbnb.config.auth.dto.SessionUser;
import com.jangsuhyun.airbnb.domain.BookedHome;
import com.jangsuhyun.airbnb.domain.BookedHomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookedHomeRepository bookedHomeRepository;

    // 숙소 예약하기
    @Transactional
    public void addBookedHome(BookedHome bookedHome) {

        bookedHomeRepository.save(bookedHome);

    }

    // user id로 현재 예약중인 숙소 찾기
    public List<BookedHome> findHomeByUserId(Long userId) {
        return bookedHomeRepository.findAllByUseridAndStatus(userId, 1); // status가 1인 숙소만 조회
    }

    // user id로 예약 취소된 숙소 찾기
    public List<BookedHome> findCanceledHomeByUserId(Long userId) {
        return bookedHomeRepository.findAllByUseridAndStatus(userId, 3); // status가 3인 숙소만 조회
    }

    // 이전 예약이 있는지 확인 후 있으면 status 2로 변경
    public void checkPreviousHome(Long userId) {

        // 1.해당 유저의 전체 예약 건 가져오기
        List<BookedHome> bookedHomeList = bookedHomeRepository.findAllByUseridAndStatus(userId, 1);

        // 2.가져온 예약건의 check out 날짜를 오늘 날짜와 비교
        for (BookedHome bookedHome : bookedHomeList) {
            LocalDate checkOut = LocalDate.parse(bookedHome.getCheckout(), DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
            LocalDate today = LocalDate.now();

            // 3.check out날짜가 지났으면 status 2로 변경
            if (checkOut.isBefore(today)) {
                bookedHome.updateStatus(2);
            }
        }
    }

    // user id로 이전 예약 숙소 찾기
    public List<BookedHome> findPreviousHomeByUserId(Long userId) {
        return bookedHomeRepository.findAllByUseridAndStatus(userId, 2); // status가 1인 숙소만 조회
    }

    //  예약 id로 예약 정보 찾기
    public BookedHome findBookedHomeById(Long id) {
        return bookedHomeRepository.findById(id).get();
    }
}
