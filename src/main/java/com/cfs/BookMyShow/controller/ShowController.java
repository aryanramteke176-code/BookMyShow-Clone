package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.dto.ShowRequest;
import com.cfs.BookMyShow.entity.Shows;
import com.cfs.BookMyShow.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping
    public ResponseEntity<Shows> addShow(@RequestBody ShowRequest request){
        return  ResponseEntity.ok(showService.addShow(request));
    }

    @GetMapping
    public ResponseEntity<List<Shows>> getAllShows(){
        return ResponseEntity.ok(showService.getAllShow());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shows> getShowById(@PathVariable Long id){
        return ResponseEntity.ok(showService.getShowById(id));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Shows>> getShowByMovieId(@PathVariable Long movieId){
        return ResponseEntity.ok(showService.getShowByMovieId(movieId));
    }

    @GetMapping("/movie/{movieId}/date")
    public ResponseEntity<List<Shows>>
    getShowByMovieAndDate(@PathVariable Long movieId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        return ResponseEntity.ok(showService.getShowByMovieIdAndDate(movieId,date));
    }



}
