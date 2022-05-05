package com.rental_backend.controller;

import com.rental_backend.dto.MovieResponse;
import com.rental_backend.entity.Movie;
import com.rental_backend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/movie")
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

    @PostMapping("/addMovieToSystem")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.CREATED);
    }

    @PostMapping("/deleteMovie")
    public ResponseEntity<?> deleteMovie(@RequestBody MovieResponse movieResponse){
        return new ResponseEntity<>(movieService.deleteMovie(movieResponse.getTitle(), movieResponse.getDirectorName()), HttpStatus.NO_CONTENT);
    }


}
