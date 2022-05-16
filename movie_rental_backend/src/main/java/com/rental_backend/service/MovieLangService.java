package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.exception.MovieNotFoundException;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieLangService {

    private MovieLangRepository movieLangRepository;
    private MovieService movieService;

    @Autowired
    public MovieLangService(MovieLangRepository movieLangRepository) {
        this.movieLangRepository = movieLangRepository;
    }

    public MovieLang addMovieLang(String mLang, Long mId ) {

        MovieLang ml = MovieLang.builder()
                .movieLang(mLang)
                .movie(movieService.findMovieById(mId))
                .build();
        return movieLangRepository.save(ml);
    }
    public void deleteMovieLang(Long mId) throws MovieNotFoundException {

        movieLangRepository.deleteMovieLang(mId);

    }

}
