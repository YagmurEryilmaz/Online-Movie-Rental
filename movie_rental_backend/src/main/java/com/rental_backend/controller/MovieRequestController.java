package com.rental_backend.controller;

import com.rental_backend.dto.MessageResponse;
import com.rental_backend.dto.MovieRequestDto;
import com.rental_backend.entity.MovieRequest;
import com.rental_backend.service.MovieRequestService;
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
    @Autowired
    public MovieRequestController(MovieRequestService movieRequestService){
        this.movieRequestService = movieRequestService;
    }

    @GetMapping("/getAllMovieRequests")
    public ResponseEntity<List<MovieRequest>> getAllMovieRequests() {
        return ResponseEntity.ok(movieRequestService.getAllMovieRequests());
    }

    @PostMapping("/addMovieRequest")
    public ResponseEntity<?> addMovieRequest(@RequestBody MovieRequestDto movieRequestDto) {
        try {
            return new ResponseEntity<>(movieRequestService.addRequest(movieRequestDto.getCustomerId(), movieRequestDto.getMovieName(), movieRequestDto.getDirectorName(), movieRequestDto.getMovieProductionYear()), HttpStatus.CREATED);
        }
        catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(new MessageResponse("Production year can not more than 2022"));
        }
    }

    @DeleteMapping("/deleteMovieRequest/{id}")
    public ResponseEntity<?> deleteMovieRequest(@PathVariable("id") Long id) {
        movieRequestService.deleteMovieRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
