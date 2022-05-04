package com.rental_backend.controller;

import com.rental_backend.entity.Movie;
import com.rental_backend.entity.MovieRequest;
import com.rental_backend.service.MovieRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class MovieRequestController {

    private MovieRequestService movieRequestService;

    @Autowired
    public MovieRequestController(MovieRequestService movieRequestService){
        this.movieRequestService = movieRequestService;
    }

    @GetMapping("/getAllMovieRequests")
    public ResponseEntity<List<MovieRequest>> getAllMovieRequests() {
        return ResponseEntity.ok(movieRequestService.getAllMovieRequests());
    }
}
