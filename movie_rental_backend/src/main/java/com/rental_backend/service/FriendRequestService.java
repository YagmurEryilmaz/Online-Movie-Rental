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
    public FriendRequest addFriendRequest(String senderEmail, String receiverEmail) {

        Customer sender = customerService.findByEmail(senderEmail);
        Customer receiver = customerService.findByEmail(receiverEmail);

        FriendRequest.PrimaryKey key = new FriendRequest.PrimaryKey(sender.getUId(), receiver.getUId());

        FriendRequest fr = FriendRequest.builder()
                .primaryKey(key)
                .receiver(receiver)
                .sender(sender)
                .friendReq_status("pending")
                .build();

        return friendRequestRepository.save(fr);
    }

    public void deleteFriendRequest(Long senderId, Long receiverId) {
        friendRequestRepository.deleteFriendRequest(senderId, receiverId);
    }


}
