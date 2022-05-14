package com.rental_backend.controller;


import com.rental_backend.dto.MessageResponse;
import com.rental_backend.dto.RentRequest;
import com.rental_backend.entity.RentedMovie;
import com.rental_backend.repository.RentedMovieRepository;
import com.rental_backend.service.CustomerService;
import com.rental_backend.service.RentedMovieService;
import com.rental_backend.service.SubtitleRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/rent")
@RequiredArgsConstructor

public class RentedMovieController {

    private final RentedMovieService rentedMovieService;

    @PostMapping("/rent")
    public ResponseEntity<?> rentMovie(@RequestBody RentRequest request)
    {
        //try {
            rentedMovieService.rentMovie(request.getCustomer(), request.getMovie(),request.getExpDate());
            return ResponseEntity.ok(new MessageResponse("rented"));
        //}
        //catch (RuntimeException r){
        //    return ResponseEntity.badRequest().body(new MessageResponse(r.getMessage()));
        //}
    }

    @GetMapping("/current/{userId}")
    public ResponseEntity<?> getCurrentlyRented(@PathVariable Long userId)
    {
        return ResponseEntity.ok(rentedMovieService.getCurrentlyRented(userId));
    }

    @GetMapping("/previous/{userId}")
    public ResponseEntity<?> getPreviouslyRented(@PathVariable Long userId)
    {
        return ResponseEntity.ok(rentedMovieService.getPreviouslyRented(userId));
    }

}
