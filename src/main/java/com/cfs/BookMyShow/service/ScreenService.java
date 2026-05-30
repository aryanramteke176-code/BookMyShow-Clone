package com.cfs.BookMyShow.service;

import com.cfs.BookMyShow.dto.ScreenRequest;
import com.cfs.BookMyShow.entity.Screen;
import com.cfs.BookMyShow.entity.Theatre;
import com.cfs.BookMyShow.repo.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenService {
    private final ScreenRepository screenRepository;
    private final TheatreService theatreService;

    public Screen addScreen(ScreenRequest request){
        Theatre theatre = theatreService.getTheatreById(request.getTheatreId());
        Screen screen = Screen.builder()
                .name(request.getName())
                .totalSeats(request.getTotalSeats())
                .theatre(theatre)
        .build();
        return screenRepository.save(screen);
    }

    public List<Screen> getAllScreen(){
        return screenRepository.findAll();
    }

    public List<Screen> getScreenByTheatre(Long theatreId){
        return screenRepository.findByTheatreId(theatreId);
    }

    public Screen getByScreenId(Long id){
        return screenRepository.findById(id).
                orElseThrow(()-> new RuntimeException("no screen found by id "+id));
    }


}
