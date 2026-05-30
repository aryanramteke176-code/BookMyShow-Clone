package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.dto.ScreenRequest;
import com.cfs.BookMyShow.entity.Screen;
import com.cfs.BookMyShow.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
@RequiredArgsConstructor
public class ScreenController {

    private final ScreenService screenService;

    @PostMapping
    public ResponseEntity<Screen> addScreen(@RequestBody ScreenRequest request){
        return ResponseEntity.ok(screenService.addScreen(request));
    }

    @GetMapping
    public ResponseEntity<List<Screen>> getAllScreens(){
        return  ResponseEntity.ok(screenService.getAllScreen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Screen> getScreenById(@PathVariable Long id){
        return  ResponseEntity.ok(screenService.getByScreenId(id));
    }

    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<Screen>> getScreenByTheatreId(@PathVariable Long theatreId){
        return ResponseEntity.ok(screenService.getScreenByTheatre(theatreId));
    }


}
