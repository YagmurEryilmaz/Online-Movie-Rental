package com.rental_backend.controller;

import com.rental_backend.dto.SuggestionDto;
import com.rental_backend.entity.Suggestion;
import com.rental_backend.service.CustomerService;
import com.rental_backend.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class SuggestionController {
    private SuggestionService suggestionService;
    private CustomerService customerService;
    @Autowired
    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/getSuggestionsByReceiver")
    public ResponseEntity<List<Suggestion>> getSuggestionsByReceiver(@RequestBody SuggestionDto suggestionDto){
        return ResponseEntity.ok(suggestionService.findSuggestionByReceiverId(suggestionDto.getMreciever_id()));
    }

    @GetMapping("/getSuggestionsBySender")
    public ResponseEntity<List<Suggestion>> getSuggestionsBySender(@RequestBody SuggestionDto suggestionDto){
        return ResponseEntity.ok(suggestionService.findSuggestionBySenderId(suggestionDto.getMsender_id()));
    }


}
