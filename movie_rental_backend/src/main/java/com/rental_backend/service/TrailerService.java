package com.rental_backend.service;
import com.rental_backend.entity.Suggestion;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrailerService {

    private TrailerRepository trailerRepository;
    private final MovieService movieService;

    @Autowired
    public TrailerService(TrailerRepository trailerRepository, MovieService movieService) {
        this.trailerRepository = trailerRepository;
        this.movieService = movieService;
    }

    public String findTrailerByMovieId(Long movieId) {
        return trailerRepository.findTrailerByMovieId(movieId);
    }

}
