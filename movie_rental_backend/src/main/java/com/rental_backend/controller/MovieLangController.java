package com.rental_backend.controller;

import com.rental_backend.entity.MovieLang;
import com.rental_backend.service.MovieLangService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/movieLang")
@RequiredArgsConstructor
public class MovieLangController {
    private MovieLangService movieLangService;
    @Autowired
    public MovieLangController(MovieLangService movieLangService){
        this.movieLangService = movieLangService;
    }

    @PostMapping("/addMovieLang/{mId}")
    public ResponseEntity<MovieLang> addMovieLang(@RequestBody MovieLang ml, @PathVariable("mId") Long mId) {
        return new ResponseEntity<>(movieLangService.addMovieLang(ml.getMovieLang(), mId), HttpStatus.CREATED);
    }

    @GetMapping("/getMovieLangByMovie/{mId}")
    public ResponseEntity<List<MovieLang>> getMovieLangByMovie(@PathVariable("mId") Long mId){
        return ResponseEntity.ok(movieLangService.getMovieLangByMovie(mId));
    }
}
