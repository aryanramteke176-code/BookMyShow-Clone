package com.cfs.BookMyShow.repo;
import com.cfs.BookMyShow.entity.Shows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShowRepository extends JpaRepository<Shows,Long> {
    List<Shows> findByMovieId(Long MovieId);
    List<Shows> findByScreenId(Long ScreenId);
    List<Shows> findByMovieIdAndShowDate(Long MovieId, LocalDate showDate);
    List<Shows> findByScreenIdAndShowDate(Long ScreenId, LocalDate showDate);
}
