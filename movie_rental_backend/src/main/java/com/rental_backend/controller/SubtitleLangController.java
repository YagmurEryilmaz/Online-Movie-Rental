package com.rental_backend.controller;

import com.rental_backend.dto.SuggestionDto;
import com.rental_backend.entity.SubtitleLang;
import com.rental_backend.entity.Suggestion;
import com.rental_backend.service.SubtitleLangService;
import com.rental_backend.service.SubtitleRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class SubtitleLangController {
    private SubtitleLangService subtitleLangService;
    @Autowired
    public SubtitleLangController(SubtitleLangService subtitleLangService){
        this.subtitleLangService = subtitleLangService;
    }

    @PostMapping("/addSubtitleLang")
    public ResponseEntity<SubtitleLang> addSubtitleLang(@RequestBody SubtitleLang s) {
        return new ResponseEntity<>(subtitleLangService.addSubtitleLang(s.getMovie().getMId(),s.getS_lang()), HttpStatus.CREATED);
    }
}
