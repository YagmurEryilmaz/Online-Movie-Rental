package com.rental_backend.controller;


import com.rental_backend.dto.MessageResponse;
import com.rental_backend.dto.RentRequest;
import com.rental_backend.entity.RentedMovie;
import com.rental_backend.service.RentedMovieService;
import lombok.RequiredArgsConstructor;
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
        System.out.println("cid " + request.getCustomer());
        System.out.println("mid " + request.getMovie());
        System.out.println("pid " + request.getPay());
        rentedMovieService.rentMovie(request.getCustomer(), request.getMovie(), request.getPay());
        return ResponseEntity.ok(new MessageResponse("rented"));
    }

    @GetMapping("/current/{id}")
    public ResponseEntity<?> getCurrentlyRented(@PathVariable Long id)
    {
        return ResponseEntity.ok(rentedMovieService.getCurrentlyRented(id));
    }

    @GetMapping("/previous/{id}")
    public ResponseEntity<?> getPreviouslyRented(@PathVariable Long id)
    {
        return ResponseEntity.ok(rentedMovieService.getPreviouslyRented(id));
    }

}
