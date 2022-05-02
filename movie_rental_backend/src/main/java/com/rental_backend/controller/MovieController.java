package com.rental_backend.controller;

import com.rental_backend.dto.MovieResponse;
import com.rental_backend.entity.Movie;
import com.rental_backend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class MovieController {

    private MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Movie>> getAllMovies() {

        return ResponseEntity.ok(movieService.getAllMovies());
    }


}
