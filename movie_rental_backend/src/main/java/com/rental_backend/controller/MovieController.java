package com.rental_backend.controller;

import com.rental_backend.dto.MovieResponse;
import com.rental_backend.entity.Movie;
import com.rental_backend.exception.MovieNotFoundException;
import com.rental_backend.service.MovieLangService;
import com.rental_backend.service.MovieService;
import com.rental_backend.service.SubtitleLangService;
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
    private SubtitleLangService subtitleLangService;
    private MovieLangService movieLangService;

    @Autowired
    public MovieController(MovieService movieService,SubtitleLangService subtitleLangService,MovieLangService movieLangService) {
        this.movieService = movieService;
        this.subtitleLangService=subtitleLangService;
        this.movieLangService=movieLangService;
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
    public ResponseEntity<Movie> addMovie(@RequestBody Movie m){
        return new ResponseEntity<>(movieService.addMovieByMObj(m),HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteMovie/{mId}")
    public ResponseEntity<?> deleteMovie(@PathVariable("mId") Long mId) throws MovieNotFoundException {
        subtitleLangService.deleteSubtitleLang(mId);
        movieLangService.deleteMovieLang(mId);
        movieService.deleteMovie(mId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/updateMoviePrice/{mId}/{price}")
    public ResponseEntity<?> updateUserInfoByUId(@PathVariable Long mId, @PathVariable double price) {
        movieService.updateMoviePrice(mId, price);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
