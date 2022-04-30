package com.rental_backend.service;

import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> findMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> findMovieByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public List<Movie> findMovieByProdYear(int prodYear) {
        return movieRepository.findByProductionYear(prodYear);
    }

    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

}
