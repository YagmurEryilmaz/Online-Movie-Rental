package com.rental_backend.controller;

import com.rental_backend.entity.MovieLang;
import com.rental_backend.entity.SubtitleLang;
import com.rental_backend.service.MovieLangService;
import com.rental_backend.service.SubtitleLangService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addMovieLang")
    public ResponseEntity<MovieLang> addMovieLang(@RequestBody MovieLang ml) {
        return new ResponseEntity<>(movieLangService.addMovieLang(ml.getMovieLang(),ml.getMovie().getMId()), HttpStatus.CREATED);
    }
}
