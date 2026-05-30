package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.dto.BookingRequest;
import com.cfs.BookMyShow.entity.Booking;
import com.cfs.BookMyShow.entity.Seats;
import com.cfs.BookMyShow.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestBody BookingRequest request){

        return ResponseEntity.ok(
                bookingService.createBooking(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                bookingService.getBookingById(id)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingByUserId(
            @PathVariable Long userId){

        return ResponseEntity.ok(
                bookingService.getBookingByUser(userId)
        );
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(
            @PathVariable Long id){

        return ResponseEntity.ok(
                bookingService.cancelBooking(id)
        );
    }

    @GetMapping("/available-seats/{showId}")
    public ResponseEntity<List<Seats>> getAvailableSeats(
            @PathVariable Long showId){

        return ResponseEntity.ok(
                bookingService.getAvailableSeats(showId)
        );
    }

}