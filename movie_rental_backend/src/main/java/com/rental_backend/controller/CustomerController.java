package com.rental_backend.controller;

import com.rental_backend.dto.CustomerDto;
import com.rental_backend.dto.FriendRequestDto;
import com.rental_backend.dto.MovieResponse;
import com.rental_backend.entity.Customer;
import com.rental_backend.service.CustomerService;
import com.rental_backend.entity.Movie;
import com.rental_backend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor

public class CustomerController {

    private CustomerService customerService;
    private MovieService movieService;

    @Autowired
    public CustomerController(CustomerService customerService, MovieService movieService) {
        this.customerService = customerService;
        this.movieService = movieService;
    }

    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @PostMapping("/searchMoviesByTitle")
    public ResponseEntity<List<Movie>> searchMoviesByTitle(@RequestBody MovieResponse movieResponse) {
        return ResponseEntity.ok(movieService.searchMovie(movieResponse.getTitle()));
    }

    @PostMapping("/searchMoviesByDirector")
    public ResponseEntity<List<Movie>> searchMoviesByDirector(@RequestBody MovieResponse movieResponse) {
        return ResponseEntity.ok(movieService.searchMovie(movieResponse.getDirectorName()));
    }

    @PostMapping("/searchMoviesByGenre")
    public ResponseEntity<List<Movie>> searchMoviesByGenre(@RequestBody MovieResponse movieResponse) {
        return ResponseEntity.ok(movieService.searchMovie(movieResponse.getGenre()));
    }

    @GetMapping("/getPendingRequests")
    public int getPendingRequests(@RequestBody FriendRequestDto friendRequestDto){
        return customerService.getPendingFriendRequestCount(friendRequestDto.getReceiver_id());
    }

    @GetMapping("/getNumOfReceivedRequests")
    public int getNumOfReceivedRequests(@RequestBody FriendRequestDto friendRequestDto){
        return customerService.getReceivedFriendRequestCount(friendRequestDto.getReceiver_id());
    }

    @GetMapping("/getNumOfSentRequests")
    public int getNumOfSentRequests(@RequestBody FriendRequestDto friendRequestDto){
        return customerService.getSentFriendRequestCount(friendRequestDto.getSender_id());
    }

    @GetMapping("/getNumOfFriends")
    public int getNumOfFriends(@RequestBody FriendRequestDto friendRequestDto){
        return customerService.getFriendCount(friendRequestDto.getReceiver_id(), friendRequestDto.getSender_id());
    }

    @DeleteMapping("/deleteUserByEmail")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable CustomerDto customerDto) {
        customerService.deleteUserByEmail(customerDto.getEmail());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteUserByUId")
    public ResponseEntity<?> deleteUserByUId(@PathVariable CustomerDto customerDto) {
        customerService.deleteUserByUId(customerDto.getUId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
