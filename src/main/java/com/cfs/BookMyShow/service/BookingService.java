package com.cfs.BookMyShow.service;

import com.cfs.BookMyShow.dto.BookingRequest;
import com.cfs.BookMyShow.entity.*;
import com.cfs.BookMyShow.enums.BookingStatus;
import com.cfs.BookMyShow.repo.BookingRepository;
import com.cfs.BookMyShow.repo.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final UserService userService;
    private final ShowService showService;

    @Transactional
    public Booking createBooking(BookingRequest request){
        User user = userService.getUserById(request.getUserId());
        Shows show = showService.getShowById(request.getShowId());

        List<Long> alreadyBookedSeats = bookingRepository.findBookedSeatsIdsByShowId(request.getShowId());

        for (Long seatId:request.getSeatIds()) {

            if(alreadyBookedSeats.contains(seatId)){
                throw new RuntimeException("Seat with id "+ seatId + "is already booked");
            }
        }
        List<Seats> seats = seatRepository.findAllById(request.getSeatIds());
        if(seats.size()!=request.getSeatIds().size()){
            throw new RuntimeException("some seats are invalid");
        }
        double totalPrice = seats.size() * show.getTicketPrice();
        Booking booking = Booking.builder()
                .user(user)
                .shows(show)
                .totalPrice(totalPrice)
                .status(BookingStatus.CONFIRMED)
                .seats(seats)
                .build();
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long id){
        return bookingRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No booking found by id "+ id));
    }

    public List<Booking> getBookingByUser(Long userId){
        return bookingRepository.findByUserId(userId);
    }

    public Booking cancelBooking(Long bookingId){
        Booking booking = getBookingById(bookingId);
        booking.setStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }

    public List<Seats> getAvailableSeats(Long showId){
        Shows shows = showService.getShowById(showId);
        List<Seats> seats = seatRepository.findByScreenId(shows.getScreen().getId());
        List<Long> bookedSeatIds = bookingRepository.findBookedSeatsIdsByShowId(showId);
        List<Seats> arr = new ArrayList<>();
        HashSet<Long> set = new HashSet<>();
        set.addAll(bookedSeatIds);
        for (Seats seats1:seats) {
            if(!set.contains(seats1.getId())){
                arr.add(seats1);
            }
        }
        return arr;
    }

}
