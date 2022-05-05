package com.rental_backend.controller;

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
@RequiredArgsConstructor
public class ReviewController {
    private ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /*
    @PostMapping("/addComment/{comment}")
    public ResponseEntity<Review> addComment(@RequestBody ReviewDto reviewDto, @PathVariable String comment) {
        return new ResponseEntity<>(reviewService.addComment(reviewDto.getReviewId(), reviewDto.getPoint(), comment), HttpStatus.CREATED);
    }
    */

}
