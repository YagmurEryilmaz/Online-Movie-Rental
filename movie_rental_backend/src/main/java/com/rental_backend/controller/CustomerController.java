package com.rental_backend.controller;

import com.rental_backend.dto.CustomerDto;
import com.rental_backend.dto.FriendRequestDto;
import com.rental_backend.dto.MovieResponse;
import com.rental_backend.entity.Customer;
import com.rental_backend.entity.Payment;
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

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/getAllCustomerEmails")
    public ResponseEntity<List<String>> getAllCustomerEmails() {
        return ResponseEntity.ok(customerService.findAllEmails());
    }

    @GetMapping("/getFriendEmails/{uId}")
    public ResponseEntity<List<String>> getFriendEmails(@PathVariable("uId") Long uId) {
        return ResponseEntity.ok(customerService.getFriendEmail(uId));
    }

    @GetMapping("/getPendingRequests")
    public int getPendingRequests(@PathVariable FriendRequestDto friendRequestDto){
        return customerService.getPendingFriendRequestCount(friendRequestDto.getReceiver_id());
    }

    @GetMapping("/getFriendEmail/{uId}")
    public List<String> getPendingRequests(@PathVariable("uId") Long uId){
        return customerService.getFriendEmail(uId);
    }

    @GetMapping("/getNumOfReceivedRequests")
    public int getNumOfReceivedRequests(@PathVariable FriendRequestDto friendRequestDto){
        return customerService.getReceivedFriendRequestCount(friendRequestDto.getReceiver_id());
    }

    @GetMapping("/getNumOfSentRequests")
    public int getNumOfSentRequests(@PathVariable FriendRequestDto friendRequestDto){
        return customerService.getSentFriendRequestCount(friendRequestDto.getSender_id());
    }

    @GetMapping("/getNumOfFriends")
    public int getNumOfFriends(@PathVariable FriendRequestDto friendRequestDto){
        return customerService.getFriendCount(friendRequestDto.getReceiver_id(), friendRequestDto.getSender_id());
    }

    @PatchMapping("/updateUserInfoByUId/{uId}/{email}")
    public ResponseEntity<?> updateUserInfoByUId(@PathVariable Long uId, @PathVariable String email) {
        customerService.updateUserByUId(uId, email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteUserByEmail")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable CustomerDto customerDto) {
        customerService.deleteUserByEmail(customerDto.getEmail());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteUserByUId/{uId}")
    public ResponseEntity<?> deleteUserByUId(@PathVariable("uId") Long uId) {
        customerService.deleteUserByUId(uId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/updateBalance/{mId}/{amount}")
    public ResponseEntity<?> updateBalance(@PathVariable("mId") Long mId, @PathVariable("amount") float amount){
        customerService.updateBalance(mId,amount);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
