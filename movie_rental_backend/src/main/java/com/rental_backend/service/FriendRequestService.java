package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FriendRequestService {

    private FriendRequestRepository friendRequestRepository;

    @Autowired
    public FriendRequestService(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }
    public List<FriendRequest> findByReceiverId(Long receiverId) {
        return friendRequestRepository.findbyReceiverId(receiverId);
    }
    public List<FriendRequest> findBySenderId(Long senderId) {
        return friendRequestRepository.findbySenderId(senderId);
    }



}
