package com.rental_backend.controller;

import com.rental_backend.entity.MovieLang;
import com.rental_backend.entity.SubtitleLang;
import com.rental_backend.service.MovieLangService;
import com.rental_backend.service.SubtitleLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class MovieLangController {
    private MovieLangService movieLangService;
    @Autowired
    public MovieLangController(MovieLangService movieLangService){
        this.movieLangService = movieLangService;
    }

    @PostMapping("/addSubtitleLang")
    public ResponseEntity<MovieLang> addSubtitleLang(@RequestBody MovieLang ml) {
        return new ResponseEntity<>(movieLangService.addMovieLang(ml.getMovieLang(),ml.getMovie().getMId()), HttpStatus.CREATED);
    }
}
