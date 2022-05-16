package com.rental_backend.controller;

import com.rental_backend.dto.SuggestionDto;
import com.rental_backend.entity.SubtitleLang;
import com.rental_backend.entity.Suggestion;
import com.rental_backend.service.SubtitleLangService;
import com.rental_backend.service.SubtitleRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/subtitleLang")
@RequiredArgsConstructor
public class SubtitleLangController {
    private SubtitleLangService subtitleLangService;
    @Autowired
    public SubtitleLangController(SubtitleLangService subtitleLangService){
        this.subtitleLangService = subtitleLangService;
    }

    @PostMapping("/addSubtitleLang/{mId}")
    public ResponseEntity<SubtitleLang> addSubtitleLang(@RequestBody SubtitleLang s, @PathVariable("mId") Long mId) {
        return new ResponseEntity<>(subtitleLangService.addSubtitleLang(mId,s.getS_lang()), HttpStatus.CREATED);
    }

    @GetMapping("/getSubtitleLangByMovie/{mId}")
    public ResponseEntity<List<SubtitleLang>> getSubtitleLangByMovie(@PathVariable("mId") Long mId){
        return ResponseEntity.ok(subtitleLangService.getSubtitleLangByMovie(mId));
    }
}
