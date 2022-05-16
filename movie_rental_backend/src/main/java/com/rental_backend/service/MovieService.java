package com.rental_backend.service;

import com.rental_backend.dto.MessageResponse;
import com.rental_backend.entity.*;
import com.rental_backend.exception.CustomerNotFoundException;
import com.rental_backend.exception.MovieAlreadyExistsException;
import com.rental_backend.exception.MovieNotFoundException;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.*;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private SubtitleLangRepository subtitleLangRepository;
    private MovieLangRepository movieLangRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository,SubtitleLangRepository subtitleLangRepository,MovieLangRepository movieLangRepository) {

        this.movieRepository = movieRepository;
        this.movieLangRepository=movieLangRepository;
        this.subtitleLangRepository=subtitleLangRepository;
    }
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findMovieById(id);
    }
    public List<String> getAllGenre(){
        return movieRepository.getAllGenre();
    }

    public Movie addMovieByMObj(Movie m) {
        return movieRepository.save(m);
    }

    public Movie addMovie(String title, String genre, String directorName, int productionYear, double price, String posterUrl, Date additionDate, List<String> subTitle, List<String> mLang, String trailerUrl)
            throws MovieAlreadyExistsException {
        if (!movieRepository.existsMovieByTitle(title) && !movieRepository.existsMovieByDirectorName(directorName)) {

            Movie movie = Movie.builder()
                    .title(title)
                    .genre(genre)
                    .directorName(directorName)
                    .productionYear(productionYear)
                    .price(price)
                    .posterUrl(posterUrl)
                    .additionDate(additionDate)
                    .trailerUrl(trailerUrl)
                    .build();
            movieRepository.save(movie);
            mLang.forEach((m) -> {
                MovieLang mL = MovieLang.builder().movieLang(m).movie(movie).build();
                movieLangRepository.save(mL);

            });
            subTitle.forEach((s) -> {
                SubtitleLang sub = SubtitleLang.builder().s_lang(s).movie(movie).build();
                subtitleLangRepository.save(sub);
            });

            return movieRepository.save(movie);
        }
        else
        {
            throw new MovieAlreadyExistsException("Movie with title " + title + " does not exist.");
        }

    }

    public void updateMoviePrice(Long mId, double price)
    {
        movieRepository.updateMoviePrice(mId,price);
    }
    public void updateTrailer(Long mId, String url)
    {
        movieRepository.updateTrailer(mId,url);
    }

    public List<Movie> findMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> findMovieByGenre(String genre) {
        return movieRepository.findByGenre(genre.toLowerCase(Locale.ROOT));
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
}
