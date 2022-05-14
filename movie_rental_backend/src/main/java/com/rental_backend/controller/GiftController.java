package com.rental_backend.controller;

import com.rental_backend.dto.FriendRequestDto;
import com.rental_backend.dto.GiftDto;
import com.rental_backend.entity.FriendRequest;
import com.rental_backend.entity.Gift;
import com.rental_backend.service.GiftService;
import com.rental_backend.service.SubtitleRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/gift")

@RequiredArgsConstructor
public class GiftController {
    private GiftService giftService;

    @Autowired
    public GiftController(GiftService giftService){
        this.giftService = giftService;
    }

    @GetMapping("/getGiftsByReceiver/{receiver_id}")
    public ResponseEntity<List<Gift>> getGiftsByReceiver(@PathVariable("receiver_id") Long receiver_id){
        return ResponseEntity.ok(giftService.findByReceiverId(receiver_id));
    }

    @GetMapping("/getGiftsBySender/{sender_id}")
    public ResponseEntity<List<Gift>> getGiftsBySender(@PathVariable("sender_id") Long sender_id){
        return ResponseEntity.ok(giftService.findBySenderId(sender_id));
    }

    @PostMapping("/createGift")
    public ResponseEntity<Gift> createGift(@RequestBody GiftDto giftDto){
        return new ResponseEntity<>(giftService.addGift(giftDto.getSender_id(), giftDto.getReceiver_id(), giftDto.getM_id()), HttpStatus.CREATED);
    }


}
