package com.cfs.BookMyShow.service;

import com.cfs.BookMyShow.dto.TheatreRequest;
import com.cfs.BookMyShow.entity.City;
import com.cfs.BookMyShow.entity.Theatre;
import com.cfs.BookMyShow.repo.TheatreRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreService {

    private final TheatreRepository theatreRepository;
    private final CityService  cityService;

    public Theatre addTheatre(TheatreRequest request){
        City city = cityService.getCityById(request.getCityId());
        Theatre theatre =Theatre.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(city)
                .build();
        return theatreRepository.save(theatre);
    }

    public List<Theatre> getAllTheatres()
    {
        return theatreRepository.findAll();
    }

    public Theatre getTheatreById(Long id){
        return theatreRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Theatre not found by "+id));
    }

    public List<Theatre> getTheatreByCity(Long cityId){
       return theatreRepository.findByCityId(cityId);
    }


}
