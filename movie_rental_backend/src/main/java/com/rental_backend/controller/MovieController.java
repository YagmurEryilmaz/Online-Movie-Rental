package com.rental_backend.controller;

import com.rental_backend.dto.MovieResponse;
import com.rental_backend.entity.Movie;
import com.rental_backend.exception.MovieNotFoundException;
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

    @GetMapping("/getMovieById/{mId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("mId") Long mId) {
        return ResponseEntity.ok(movieService.findById(mId));
    }


    @PostMapping("/addMovieToSystem")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteMovie")
    public ResponseEntity<?> deleteMovie(@PathVariable MovieResponse movieResponse) throws MovieNotFoundException {
        movieService.deleteMovie(movieResponse.getTitle(), movieResponse.getDirectorName());
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/updateMoviePrice/{mId}/{price}")
    public ResponseEntity<?> updateUserInfoByUId(@PathVariable Long mId, @PathVariable double price) {
        movieService.updateMoviePrice(mId, price);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
