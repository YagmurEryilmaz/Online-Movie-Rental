package com.rental_backend.controller;
 import com.rental_backend.dto.FriendRequestDto;
 import com.rental_backend.entity.FriendRequest;
 import com.rental_backend.service.CustomerService;
 import com.rental_backend.service.FriendRequestService;
 import lombok.RequiredArgsConstructor;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;
 import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/friendrequest")
@RequiredArgsConstructor
public class FriendRequestController {

    private FriendRequestService friendRequestService;
    private CustomerService customerService;
    @Autowired
    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @GetMapping("/getFriendRequestsByReceiver")
    public ResponseEntity<List<FriendRequest>> getRequestsByReceiver(@RequestBody FriendRequestDto friendRequestDto){
        return ResponseEntity.ok(friendRequestService.findByReceiverId(friendRequestDto.getReceiver_id()));
    }

    @GetMapping("/getFriendRequestsBySender")
    public ResponseEntity<List<FriendRequest>> getRequestsBySender(@RequestBody FriendRequestDto friendRequestDto){
        return ResponseEntity.ok(friendRequestService.findBySenderId(friendRequestDto.getSender_id()));
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

}

