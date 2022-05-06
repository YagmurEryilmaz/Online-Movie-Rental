package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GiftService {

    private GiftRepository giftRepository;
    private CustomerService customerService;
    private MovieService movieService;
    private PaymentService paymentService;

    @Autowired
    public GiftService(GiftRepository giftRepository, CustomerService customerService, MovieService movieService, PaymentService paymentService) {
        this.giftRepository = giftRepository;
        this.customerService = customerService;
        this.movieService = movieService;
        this.paymentService = paymentService;
    }

    public List<Gift> findByReceiverId(Long receiverId) {
        return giftRepository.findbyReceiverId(receiverId);
    }
    public List<Gift> findBySenderId(Long senderId) {
        return giftRepository.findbySenderId(senderId);
    }
    public Gift addGift(Long sender_id, Long receiver_id, Long m_id, Long pay_id) {
        Gift.PrimaryKey primaryKey = new Gift.PrimaryKey(sender_id,receiver_id,m_id,pay_id);

        Gift gift = Gift.builder()
                .primaryKey(primaryKey)
                .senderCustomer(customerService.findById(sender_id))
                .receiverCustomer(customerService.findById(receiver_id))
                .movie(movieService.findMovieById(m_id))
                .payment(paymentService.findById(pay_id))
                .build();
        return giftRepository.save(gift);
    }

}
