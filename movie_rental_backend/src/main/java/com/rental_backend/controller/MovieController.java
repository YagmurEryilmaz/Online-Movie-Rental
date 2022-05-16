package com.rental_backend.controller;

import com.rental_backend.dto.MovieResponse;
import com.rental_backend.entity.Movie;
import com.rental_backend.exception.MovieNotFoundException;
import com.rental_backend.service.MovieLangService;
import com.rental_backend.service.MovieService;
import com.rental_backend.service.SubtitleLangService;
//import com.rental_backend.service.TrailerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/movie")
@RequiredArgsConstructor
public class MovieController {

    private MovieService movieService;
    private SubtitleLangService subtitleLangService;
    private MovieLangService movieLangService;
    //private TrailerService trailerService;

    @Autowired
    public MovieController(MovieService movieService,SubtitleLangService subtitleLangService,MovieLangService movieLangService) {//TrailerService trailerService) {
        this.movieService = movieService;
        this.subtitleLangService=subtitleLangService;
        this.movieLangService=movieLangService;
        //this.trailerService=trailerService;
    }

    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/getMovieById/{mId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("mId") Long mId) {
        return ResponseEntity.ok(movieService.findById(mId));
    }

    @GetMapping("/getMovieByGenre/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable("genre") String genre) {
        return ResponseEntity.ok(movieService.findMovieByGenre(genre));
    }

    @PostMapping("/addMovieToSystem")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieResponse m){
        return new ResponseEntity<>(movieService.addMovie(
                m.getTitle(),
                m.getGenre(),
                m.getDirectorName(),
                m.getProductionYear(),
                m.getPrice(),
                m.getPosterUrl(),
                m.getAdditionDate(),
                m.getSLang(),
                m.getMLang(),
                m.getTrailerUrl())
                ,HttpStatus.CREATED);
    }


    @DeleteMapping("/deleteMovie/{mId}")
    public ResponseEntity<?> deleteMovie(@PathVariable("mId") Long mId) throws MovieNotFoundException {
        subtitleLangService.deleteSubtitleLang(mId);
        movieLangService.deleteMovieLang(mId);
        //trailerService.deleteTrailer(mId);
        movieService.deleteMovie(mId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/updateMoviePrice/{mId}/{price}")
    public ResponseEntity<?> updateUserInfoByUId(@PathVariable("mId") Long mId, @PathVariable ("price") double price) {
        movieService.updateMoviePrice(mId, price);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/updateTrailer/{mId}")
    public ResponseEntity<?> updateUserInfoByUId(@PathVariable("mId") Long mId, @RequestBody MovieResponse m){
        movieService.updateTrailer(mId,m.getTrailerUrl());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAllGenre")
    public ResponseEntity<List<String>> getAllGenre(){
        return ResponseEntity.ok(movieService.getAllGenre());
    }

}
