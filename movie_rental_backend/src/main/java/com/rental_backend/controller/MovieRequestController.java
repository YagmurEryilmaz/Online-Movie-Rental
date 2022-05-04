package com.rental_backend.controller;

import com.rental_backend.dto.MovieRequestDto;
import com.rental_backend.entity.Movie;
import com.rental_backend.entity.MovieRequest;
import com.rental_backend.service.CustomerService;
import com.rental_backend.service.MovieRequestService;
import com.rental_backend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/movieRequest")

@RequiredArgsConstructor
public class MovieRequestController {

    private MovieRequestService movieRequestService;
    private MovieService movieService;
    private MovieRequest movieRequest;
    private CustomerService customerService;

    @Autowired
    public MovieRequestController(MovieRequestService movieRequestService){
        this.movieRequestService = movieRequestService;
    }

    @GetMapping("/getAllMovieRequests")
    public ResponseEntity<List<MovieRequest>> getAllMovieRequests() {
        return ResponseEntity.ok(movieRequestService.getAllMovieRequests());
    }

    @PostMapping("/addMovieRequest")
    public ResponseEntity<MovieRequest> addMovieRequest(@RequestBody MovieRequest movieReq) {
        return new ResponseEntity<>(movieRequestService.addRequest(movieReq), HttpStatus.CREATED);
    }
}
