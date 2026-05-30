package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.dto.TheatreRequest;
import com.cfs.BookMyShow.entity.Theatre;
import com.cfs.BookMyShow.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @PostMapping
    public ResponseEntity<Theatre> addTheatre(@RequestBody TheatreRequest request){
        return ResponseEntity.ok(theatreService.addTheatre(request));
    }

    @GetMapping
    public ResponseEntity<List<Theatre>> getAllTheatres(){
        return ResponseEntity.ok(theatreService.getAllTheatres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable Long id){
        return ResponseEntity.ok(theatreService.getTheatreById(id));
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<Theatre>> getTheatreByCityId(@PathVariable Long cityId){
        return ResponseEntity.ok(theatreService.getTheatreByCity(cityId));
    }
}
