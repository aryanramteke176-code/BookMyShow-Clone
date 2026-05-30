package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.dto.SeatRequest;
import com.cfs.BookMyShow.entity.Seats;
import com.cfs.BookMyShow.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @PostMapping
    public ResponseEntity<Seats> addSeat(@RequestBody SeatRequest request){
        return ResponseEntity.ok(seatService.addSeat(request));
    }

    @GetMapping("/screen/{screenId}")
    public ResponseEntity<List<Seats>> getSeatsByScreenId(@PathVariable Long screenId){
        return  ResponseEntity.ok(seatService.getSeatsByScreenId(screenId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seats> getSeatsById(@PathVariable Long id){
        return  ResponseEntity.ok(seatService.getSeatById(id));
    }


}
