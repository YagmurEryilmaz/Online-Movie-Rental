package com.rental_backend.controller;

import com.rental_backend.dto.MessageResponse;
import com.rental_backend.dto.PaymentDto;
import com.rental_backend.entity.Payment;
import com.rental_backend.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/payment")

@RequiredArgsConstructor
public class PaymentController {
    private PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    /*@GetMapping("/getPaymentById")
    public ResponseEntity<Payment> getPaymentById(@RequestBody PaymentDto paymentDto){
        return ResponseEntity.ok(paymentService.findById(paymentDto.getPayId()));
    }*/

    @PostMapping("/pay")
    public ResponseEntity<?> payMovie(@RequestBody PaymentDto paymentDto)
    {
        try {
            paymentService.pay(paymentDto.getCustomerEmail(), paymentDto.getMovie(), paymentDto.getPayType(), paymentDto.getExpDate());
            return ResponseEntity.ok(new MessageResponse("paid"));
        }
        catch (RuntimeException r){
            return ResponseEntity.badRequest().body(new MessageResponse(r.getMessage()));
        }
    }
}
