package com.cfs.BookMyShow.service;

import com.cfs.BookMyShow.dto.SeatRequest;
import com.cfs.BookMyShow.entity.Seats;
import com.cfs.BookMyShow.repo.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final ScreenService screenService;

    public Seats addSeat(SeatRequest seatRequest){
        Seats seat = Seats.builder()
                .seatNumber(seatRequest.getSeatNumber())
                .col(seatRequest.getCol())
                .row(seatRequest.getRow())
                .seatType(seatRequest.getSeatType())
                .screen(screenService.getByScreenId(seatRequest.getScreenId()))
        .build();

        return seatRepository.save(seat);
    }

    public List<Seats> getAllSeats(){
        return seatRepository.findAll();
    }

    public Seats getSeatById(Long id){
        return seatRepository.findById(id).orElseThrow(()-> new RuntimeException("No seat found with id "+ id ));
    }

    public List<Seats> getSeatsByScreenId(Long id){
        return  seatRepository.findByScreenId(id);
    }
}
