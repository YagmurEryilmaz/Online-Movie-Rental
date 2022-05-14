package com.rental_backend.controller;

import com.rental_backend.dto.FriendRequestDto;
import com.rental_backend.dto.MessageResponse;
import com.rental_backend.dto.PaymentDto;
import com.rental_backend.dto.RentRequest;
import com.rental_backend.entity.FriendRequest;
import com.rental_backend.entity.Movie;
import com.rental_backend.entity.MovieRequest;
import com.rental_backend.entity.Payment;
import com.rental_backend.repository.PaymentRepository;
import com.rental_backend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/payment")

@RequiredArgsConstructor
public class PaymentController {
    private PaymentService paymentService;
    private RentedMovieService rentedMovieService;

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/getPaymentById")
    public ResponseEntity<Payment> getPaymentById(@RequestBody PaymentDto paymentDto){
        return ResponseEntity.ok(paymentService.findById(paymentDto.getPayId()));
    }

    @PostMapping("/pay")
    public ResponseEntity<?> payMovie(@RequestBody PaymentDto paymentDto, @RequestBody RentRequest request)
    {
        try {
            paymentService.pay(request.getCustomer(), request.getMovie(), paymentDto.getPayId(), paymentDto.getPayType(), request.getExpDate());
            return ResponseEntity.ok(new MessageResponse("paid"));
        }
        catch (RuntimeException r){
            return ResponseEntity.badRequest().body(new MessageResponse(r.getMessage()));
        }
    }
}
