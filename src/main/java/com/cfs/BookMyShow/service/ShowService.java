package com.cfs.BookMyShow.service;

import com.cfs.BookMyShow.dto.ShowRequest;
import com.cfs.BookMyShow.entity.Movie;
import com.cfs.BookMyShow.entity.Screen;
import com.cfs.BookMyShow.entity.Shows;
import com.cfs.BookMyShow.repo.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private  final MovieService movieService;
    private final ScreenService screenService;

    public Shows addShow(ShowRequest request){
        Movie movie = movieService.getMoviesById(request.getMovieId());
        Screen screen = screenService.getByScreenId(request.getScreenId());

        Shows show = Shows.builder()
                .showDate(request.getShowDate())
                .endTime(request.getEndTime())
                .ticketPrice(request.getTicketPrice())
                .movie(movie)
                .screen(screen)
                .startTime(request.getStartTime())
        .build();

        return showRepository.save(show);
    }

    public List<Shows> getAllShow(){
        return showRepository.findAll();
    }

    public Shows getShowById(Long id){
        return showRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No show found by id "+id));
    }

    public List<Shows> getShowByMovieId(Long movieId){
        return showRepository.findByMovieId(movieId);
    }

    public List<Shows> getShowByMovieIdAndDate(Long movieId , LocalDate date){
       return showRepository.findByMovieIdAndShowDate(movieId,date);
    }

    public List<Shows> getShowByScreenId(Long id){
        return showRepository.findByScreenId(id);
    }
}
