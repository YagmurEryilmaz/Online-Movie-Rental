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
    private RentedMovieRepository rentedMovieRepository;
    private RentedMovieService rentedMovieService;
    private CustomerService customerService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, RentedMovieRepository rentedMovieRepository, @Lazy RentedMovieService rentedMovieService, CustomerService customerService) {
        this.paymentRepository = paymentRepository;
        this.rentedMovieRepository = rentedMovieRepository;
        this.rentedMovieService = rentedMovieService;
        this.customerService = customerService;
    }

    public Payment findById(long pay_id) {
        return paymentRepository.findByPayId(pay_id);
    }

    public void pay(String email, Long m_id, Long payId, String payType, Date expDate)
    {
        Long c_id = customerService.findByEmail(email).getUId();
        if(rentedMovieService.isRentedPreviously(c_id,m_id)) {
            throw new RuntimeException("Already Paid");
        }else {
            Payment payment = Payment.builder()
                    .payId(payId)
                    .payType(payType)
                    .payStatus("paid")
                    .build();
            paymentRepository.save(payment);
            rentedMovieService.rentMovie(c_id, m_id,  expDate);
        }

    }

}
