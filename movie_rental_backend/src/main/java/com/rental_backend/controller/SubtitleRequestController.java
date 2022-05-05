package com.rental_backend.controller;

import com.rental_backend.entity.MovieRequest;
import com.rental_backend.entity.SubtitleRequest;
import com.rental_backend.service.MovieRequestService;
import com.rental_backend.service.SubtitleRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;



public class SubtitleRequestController {

    private SubtitleRequestService subtitleRequestService;
    @Autowired
    public SubtitleRequestController(SubtitleRequestService subtitleRequestService){
        this.subtitleRequestService = subtitleRequestService;
    }

    @GetMapping("/getAllSubtitleRequests")
    public ResponseEntity<List<SubtitleRequest>> getAllSubtitleRequests() {
        return ResponseEntity.ok(subtitleRequestService.getAllSubtitleRequests());
    }

    @PostMapping("/addSubtitleRequest")
    public ResponseEntity<SubtitleRequest> addSubtitleRequest(@RequestBody SubtitleRequest subtitleReq) {
        return new ResponseEntity<>(subtitleRequestService.addSubtitleRequest(subtitleReq), HttpStatus.CREATED);
    }
}
