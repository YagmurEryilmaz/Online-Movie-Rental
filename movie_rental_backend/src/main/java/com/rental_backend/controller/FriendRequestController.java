package com.rental_backend.controller;
 import com.rental_backend.dto.FriendRequestDto;
 import com.rental_backend.entity.FriendRequest;
 import com.rental_backend.entity.Suggestion;
 import com.rental_backend.service.CustomerService;
 import com.rental_backend.service.FriendRequestService;
 import lombok.RequiredArgsConstructor;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;
 import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/friendRequest")
@RequiredArgsConstructor

public class FriendRequestController {

    private FriendRequestService friendRequestService;
    private CustomerService customerService;
    @Autowired
    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @GetMapping("/getFriendRequestsByReceiver")
    public ResponseEntity<List<FriendRequest>> getRequestsByReceiver(@PathVariable FriendRequestDto friendRequestDto){
        return ResponseEntity.ok(friendRequestService.findByReceiverId(friendRequestDto.getReceiver_id()));
    }

    @GetMapping("/getFriendRequestsBySender")
    public ResponseEntity<List<FriendRequest>> getRequestsBySender(@PathVariable FriendRequestDto friendRequestDto){
        return ResponseEntity.ok(friendRequestService.findBySenderId(friendRequestDto.getSender_id()));
    }

    @PostMapping("/createFriendRequest")
    public ResponseEntity<FriendRequest> createFriendRequest(@RequestBody FriendRequestDto fr) {
        return new ResponseEntity<>(friendRequestService.addFriendRequest(fr.getSender_email(), fr.getReceiver_email()), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteFriendRequest")
    public ResponseEntity<?> deleteFriendRequest(@PathVariable FriendRequestDto fr) {
        friendRequestService.deleteFriendRequest(fr.getSender_id(),fr.getReceiver_id());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

