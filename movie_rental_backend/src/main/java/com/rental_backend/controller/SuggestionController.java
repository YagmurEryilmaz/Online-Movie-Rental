package com.rental_backend.controller;

import com.rental_backend.dto.MovieRequestDto;
import com.rental_backend.dto.SuggestionDto;
import com.rental_backend.entity.MovieRequest;
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

    @GetMapping("/getSuggestionsByReceiver")
    public ResponseEntity<List<Suggestion>> getSuggestionsByReceiver(@PathVariable SuggestionDto suggestionDto){
        return ResponseEntity.ok(suggestionService.findSuggestionByReceiverId(suggestionDto.getMreceiver_id()));
    }

    @PostMapping("/addSuggestion")
    public ResponseEntity<Suggestion> addSuggestion(@RequestBody SuggestionDto suggestionDto) {
        return new ResponseEntity<>(suggestionService.addSuggestion(suggestion, suggestionDto.getM_id()), HttpStatus.CREATED);
    }
/*
    @GetMapping("/getSuggestionsBySender")
    public ResponseEntity<List<Suggestion>> getSuggestionsBySender(@RequestBody SuggestionDto suggestionDto){
        return ResponseEntity.ok(suggestionService.findSuggestionBySenderId(suggestionDto.getMsender_id()));
    }*/


}
