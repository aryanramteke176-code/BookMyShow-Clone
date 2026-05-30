package com.cfs.BookMyShow.service;

import com.cfs.BookMyShow.entity.Movie;
import com.cfs.BookMyShow.repo.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMoviesById(Long id){
        return movieRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No movie found by id "+id));
    }

    public List<Movie> searchByTitle(String title){
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> getByGenre(String genre){
        return movieRepository.findByGenre(genre);
    }

    public List<Movie> getByLanguage(String language){
        return movieRepository.findByLanguage(language);
    }

    public Movie updateMovie(Long id,Movie updatedMovie){
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("no movie found"));
        if(updatedMovie.getGenre()!=null) {
            existingMovie.setGenre(updatedMovie.getGenre());
        }
        if(updatedMovie.getDescription()!=null){
            existingMovie.setDescription(updatedMovie.getDescription());
        }
        if(updatedMovie.getRating()!=null){
            existingMovie.setRating(updatedMovie.getRating());
        }
        if(updatedMovie.getLanguage()!=null){
            existingMovie.setLanguage(updatedMovie.getLanguage());
        }
        if(updatedMovie.getTitle()!=null){
            existingMovie.setTitle(updatedMovie.getTitle());
        }
        if(updatedMovie.getReleaseDate()!=null){
            existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
        }
        if(updatedMovie.getDurationMinutes()!=null){
            existingMovie.setDurationMinutes(updatedMovie.getDurationMinutes());
        }
        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Long id){
        Movie movie = getMoviesById(id);
        movieRepository.delete(movie);
    }

}
