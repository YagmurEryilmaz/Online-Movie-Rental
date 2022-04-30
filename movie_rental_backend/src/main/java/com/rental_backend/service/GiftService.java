package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GiftService {

    private GiftRepository giftRepository;

    @Autowired
    public GiftService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public List<Gift> findByReceiverId(Long receiverId) {
        return giftRepository.findbyReceiverId(receiverId);
    }
    public List<Gift> findBySenderId(Long senderId) {
        return giftRepository.findbySenderId(senderId);
    }

}
