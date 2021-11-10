package com.jangsuhyun.airbnb.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookedHomeRepository extends JpaRepository<BookedHome, Long> {
    List<BookedHome> findAllByUseridAndStatus(Long userId, int i);
}
