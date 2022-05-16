package com.rental_backend.controller;
 import com.rental_backend.dto.FriendRequestDto;
 import com.rental_backend.dto.MessageResponse;
 import com.rental_backend.entity.FriendRequest;
 import com.rental_backend.service.CustomerService;
 import com.rental_backend.service.FriendRequestService;
 import lombok.RequiredArgsConstructor;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.jpa.repository.Modifying;
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
    @Autowired
    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @GetMapping("/getFriendRequestsByReceiver/{receiver_id}")

    public ResponseEntity<List<FriendRequest>> getRequestsByReceiver(@PathVariable("receiver_id") Long receiver_id){
        return ResponseEntity.ok(friendRequestService.findByReceiverId(receiver_id));
    }

    @GetMapping("/getFriendRequestsBySender/{sender_id}")
    public ResponseEntity<List<FriendRequest>> getRequestsBySender(@PathVariable("sender_id") Long sender_id) {
        return ResponseEntity.ok(friendRequestService.findBySenderId(sender_id));
    }

    @GetMapping("/getFriends/{userId}")
    public ResponseEntity<List<FriendRequest>> getFriends(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(friendRequestService.getFriends(userId));
    }

    @GetMapping("/getAcceptedRequests/{sender_id}/{receiver_id}")
    public ResponseEntity<List<FriendRequest>> getAcceptedRequests(@PathVariable("sender_id") Long sender_id,@PathVariable("receiver_id") Long receiver_id) {
        return ResponseEntity.ok(friendRequestService.getAcceptedRequests(sender_id,receiver_id));
    }

    @PostMapping("/createFriendRequest")
    public ResponseEntity<FriendRequest> createFriendRequest(@RequestBody FriendRequestDto fr) {
        return new ResponseEntity<>(friendRequestService.addFriendRequest(fr.getSender_email(), fr.getReceiver_email()), HttpStatus.CREATED);
    }

    @Modifying
    @PostMapping("/accept")
    public ResponseEntity<?> acceptRequest(@RequestBody FriendRequestDto fr){
        friendRequestService.acceptRequest(fr.getSender_email(), fr.getReceiver_id());
        return ResponseEntity.ok(new MessageResponse("request accepted"));
    }

    @Modifying
    @PostMapping("/reject")
    public ResponseEntity<?> rejectRequest(@RequestBody FriendRequestDto fr){
        friendRequestService.rejectRequest(fr.getSender_email(), fr.getReceiver_id());
        return ResponseEntity.ok(new MessageResponse("request rejected"));
    }
    @DeleteMapping("/deleteFriendRequest")
    public ResponseEntity<?> deleteFriendRequest(@PathVariable FriendRequestDto fr) {
        friendRequestService.deleteFriendRequest(fr.getSender_id(),fr.getReceiver_id());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

