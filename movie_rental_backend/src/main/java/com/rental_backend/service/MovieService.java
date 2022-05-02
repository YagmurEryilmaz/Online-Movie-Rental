package com.rental_backend.service;

import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findMovieById(id);
    }
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
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

    public List<Movie> searchMovie(String searchTerm) {
        return movieRepository.search(searchTerm);
    }
/*
    public List<Movie> deleteMovie(Long movieId) {
        return movieRepository.deleteMovie(movieId);
    }
 */

    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public Movie addRented(Long m_id, RentedMovie rm){
        Movie movie = movieRepository.findMovieById(m_id);
        movie.addRented(rm);
        return movieRepository.save(movie);
    }


}
