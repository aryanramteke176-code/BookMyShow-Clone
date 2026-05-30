package com.cfs.BookMyShow.repo;

import com.cfs.BookMyShow.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seats,Long> {
    List<Seats> findByScreenId(Long ScreenId);
}
