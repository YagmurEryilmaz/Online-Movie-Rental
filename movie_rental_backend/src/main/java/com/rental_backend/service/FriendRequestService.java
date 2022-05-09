package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FriendRequestService {

    private FriendRequestRepository friendRequestRepository;
    private CustomerService customerService;

    @Autowired
    public FriendRequestService(FriendRequestRepository friendRequestRepository, CustomerService customerService) {
        this.friendRequestRepository = friendRequestRepository;
        this.customerService = customerService;
    }
    public List<FriendRequest> findByReceiverId(Long receiverId) {
        return friendRequestRepository.findbyReceiverId(receiverId);
    }
    public List<FriendRequest> findBySenderId(Long senderId) {
        return friendRequestRepository.findbySenderId(senderId);
    }
    public FriendRequest addFriendRequest(Long senderId, Long receiverId) {

        FriendRequest.PrimaryKey key = new FriendRequest.PrimaryKey(senderId, receiverId);

        FriendRequest fr = FriendRequest.builder()
                .primaryKey(key)
                .receiver(customerService.findById(receiverId))
                .sender(customerService.findById(senderId))
                .friendReq_status("pending")
                .build();

        return friendRequestRepository.save(fr);
    }

    public void deleteFriendRequest(Long senderId, Long receiverId) {
        friendRequestRepository.deleteFriendRequest(senderId, receiverId);
    }


}
