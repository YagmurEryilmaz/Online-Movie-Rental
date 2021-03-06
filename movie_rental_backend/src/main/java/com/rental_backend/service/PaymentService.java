package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private RentedMovieService rentedMovieService;
    private CustomerService customerService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository,@Lazy RentedMovieService rentedMovieService, CustomerService customerService) {
        this.paymentRepository = paymentRepository;
        this.rentedMovieService = rentedMovieService;
        this.customerService = customerService;
    }

    public Payment findById(long pay_id) {
        return paymentRepository.findByPayId(pay_id);
    }

    public void pay(String email, Long m_id, String payType, Date expDate)
    {
        Long c_id = customerService.findByEmail(email).getUId();
        if(rentedMovieService.isRentedCurrently(c_id, m_id)  ) {
            throw new RuntimeException("Already rented");
        }else {
            Payment payment = Payment.builder()
                    .payType(payType)
                    .payStatus("paid")
                    .build();
            paymentRepository.save(payment);
            rentedMovieService.rentMovie(c_id, m_id,  expDate);
        }

    }

}
