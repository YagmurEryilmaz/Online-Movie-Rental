package com.rental_backend.service;

import com.rental_backend.entity.*;
import com.rental_backend.exception.MovieNotFoundException;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public List<Movie> getBySubtitleLang(String sLang) {
        return movieRepository.findBySubtitleLang(sLang);
    }

    public Movie addMovie(String title, String genre, String directorName, int productionYear, double price, String posterUrl, Date additionDate, Set<MovieLang> movieLang, Set<SubtitleLang> subtitleLang, Set<Trailer> trailer) {

            Movie s = Movie.builder()
                    .title(title)
                    .genre(genre)
                    .directorName(directorName)
                    .productionYear(productionYear)
                    .price(price)
                    .posterUrl(posterUrl)
                    .additionDate(additionDate)
                    .subtitleLang(subtitleLang)
                    .movieLang(movieLang)
                    .trailers(trailer)
                    .build();
            return movieRepository.save(s);

    }

    public void updateMoviePrice(Long mId, double price)
    {
        movieRepository.updateMoviePrice(mId,price);
    }

    public List<Movie> findMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> findMovieByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public Movie findById(long id) {
        return movieRepository.findById(id);
    }

    public List<Movie> findMovieByProdYear(int prodYear) {
        return movieRepository.findByProductionYear(prodYear);
    }

    public List<Movie> searchMovie(String searchTerm) {
        return movieRepository.search(searchTerm);
    }

    public void deleteMovie(String title, String directorName) throws MovieNotFoundException {
        if (movieRepository.existsByTitleAndDirectorName(title,directorName)) {
            movieRepository.deleteMovie(title, directorName);
        }
        else {
            throw new MovieNotFoundException("Movie with title " + title + " does not exist.");
        }
    }

    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public Movie addRented(Long m_id, RentedMovie rm){
        Movie movie = movieRepository.findMovieById(m_id);
        movie.addRented(rm);
        return movieRepository.save(movie);
    }


}
