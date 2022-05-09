/*package com.rental_backend.controller;

import com.rental_backend.dto.ReviewDto;
import com.rental_backend.entity.Movie;
import com.rental_backend.entity.Review;
import com.rental_backend.service.MovieService;
import com.rental_backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/review")
@RequiredArgsConstructor*/
/*public class ReviewController {
    private ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/addReview")
    public ResponseEntity<Review> addReview(@RequestBody ReviewDto reviewDto, @RequestBody String comment) {
        return new ResponseEntity<>(reviewService.addReview(reviewDto.getReviewId(), reviewDto.getPoint(), comment), HttpStatus.CREATED);
    }
    @PostMapping("/addOnlyPoints")
    public ResponseEntity<Review> addOnlyPoints(@RequestBody ReviewDto reviewDto, @RequestBody int point) {
        return new ResponseEntity<>(reviewService.addOnlyPoints(reviewDto.getReviewId(),point), HttpStatus.CREATED);
    }



}*/
