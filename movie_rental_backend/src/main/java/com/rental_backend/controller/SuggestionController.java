package com.rental_backend.controller;

import com.rental_backend.dto.SuggestionDto;
import com.rental_backend.entity.Suggestion;
import com.rental_backend.service.CustomerService;
import com.rental_backend.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/suggestion")
@RequiredArgsConstructor

public class SuggestionController {
    private SuggestionService suggestionService;
    private CustomerService customerService;
    private Suggestion suggestion;

    @Autowired
    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/getSuggestionsByReceiver/{receiverId}")
    public ResponseEntity<List<Suggestion>> getSuggestionsByReceiver(@PathVariable("receiverId") Long receiverId){
        return ResponseEntity.ok(suggestionService.findSuggestionByReceiverId(receiverId));
    }

    @PostMapping("/addSuggestion")
    public ResponseEntity<Suggestion> addSuggestion(@RequestBody SuggestionDto s) {
        return new ResponseEntity<>(suggestionService.addSuggestion(s.getSenderId(), s.getReceiverId(), s.getMovieId()), HttpStatus.CREATED);
    }

    @GetMapping("/getSuggestionsBySender/{senderId}")
    public ResponseEntity<List<Suggestion>> getSuggestionsBySender(@PathVariable Long senderId){
        return ResponseEntity.ok(suggestionService.findSuggestionBySenderId(senderId));
    }


}
