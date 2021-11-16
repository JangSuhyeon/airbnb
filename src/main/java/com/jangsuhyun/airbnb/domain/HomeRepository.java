package com.jangsuhyun.airbnb.domain;

import com.jangsuhyun.airbnb.controller.dto.HomeSearchRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface HomeRepository extends JpaRepository<Home, Long> {

    List<Home> findByStartDayLessThanEqualAndEndDayGreaterThanEqualAndGuestGreaterThanEqual(Date startDay, Date endDay, int guestCnt);
}
