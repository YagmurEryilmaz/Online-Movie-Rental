package com.rental_backend.controller;


import com.rental_backend.dto.MessageResponse;
import com.rental_backend.dto.RateRequest;
import com.rental_backend.dto.RateResponse;
import com.rental_backend.entity.Rate;
import com.rental_backend.service.RateService;
import com.rental_backend.service.RentedMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/rate")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @PostMapping("/rateMovie")
    public ResponseEntity rateMovie(@RequestBody RateRequest request){

        try{
            rateService.rateMovie(request.getMovie(), request.getCustomer(), request.getPoint());
            return ResponseEntity.ok(new MessageResponse("rate"));
        }
        catch (RuntimeException r){
            return ResponseEntity.badRequest().body(new MessageResponse("cannot rate movies you haven't watched"));
        }

    }

    @GetMapping("/getRatesByMovie")
    public ResponseEntity<List<RateResponse>> getRatesByMovie(@PathVariable Long movieId){

        List <RateResponse> response = new ArrayList<>();
        rateService.getRatesByMovie(movieId).stream().forEach((r) -> response.add(new RateResponse(r)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAveragePoint/{movieId}")
    public ResponseEntity<?> getAveragePoint(@PathVariable("movieId") Long movieId){
        return ResponseEntity.ok(rateService.getAveragePoint(movieId));
    }


}
