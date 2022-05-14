package com.rental_backend.controller;

import com.rental_backend.entity.Suggestion;
import com.rental_backend.service.SuggestionService;
import com.rental_backend.service.TrailerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/trailer")

@RequiredArgsConstructor
public class TrailerController {

    private TrailerService trailerService;
    public TrailerController(TrailerService trailerService) {
        this.trailerService = trailerService;
    }

    @GetMapping("/getTrailerByMovie/{movieId}")
    public ResponseEntity<String> getTrailerByMovie(@PathVariable Long movieId){
        return ResponseEntity.ok(trailerService.findTrailerByMovieId(movieId));
    }
}
