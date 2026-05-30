package com.cfs.BookMyShow.repo;

import com.cfs.BookMyShow.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findByShowsId(Long showId);

    @Query(
            "SELECT s.id FROM Booking b JOIN b.seats s " +
                    "WHERE b.shows.id = :showId AND b.status='CONFIRMED'"
    )
    List<Long> findBookedSeatsIdsByShowId(
            @Param("showId") Long showId
    );
}