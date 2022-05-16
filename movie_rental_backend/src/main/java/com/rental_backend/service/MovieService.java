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
    private SubtitleLangRepository subtitleLangRepository;
    private MovieLangRepository movieLangRepository;
    //private TrailerRepository trailerRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository,SubtitleLangRepository subtitleLangRepository,MovieLangRepository movieLangRepository) {

        this.movieRepository = movieRepository;
        this.movieLangRepository=movieLangRepository;
        this.subtitleLangRepository=subtitleLangRepository;
        //this.trailerRepository=trailerRepository;

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

    public Movie addMovieByMObj(Movie m) {
        return movieRepository.save(m);
    }

    public Movie addMovie(String title, String genre, String directorName, int productionYear, double price, String posterUrl, Date additionDate) {

            Movie s = Movie.builder()
                    .title(title)
                    .genre(genre)
                    .directorName(directorName)
                    .productionYear(productionYear)
                    .price(price)
                    .posterUrl(posterUrl)
                    .additionDate(additionDate)
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

    public void deleteMovie(Long mId) throws MovieNotFoundException {
        if (movieRepository.existsById(mId)) {
            subtitleLangRepository.deleteSubtitleLang(mId);
            movieLangRepository.deleteMovieLang(mId);
            movieRepository.deleteMovie(mId);

        }
        else {
            throw new MovieNotFoundException("Movie with id " + mId + " does not exist.");
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
