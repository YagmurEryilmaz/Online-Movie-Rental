package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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

    public void acceptRequest(Long senderId, Long receiverId)
    {
        FriendRequest request = friendRequestRepository.findRequest(senderId,receiverId);
        request.setFriendReq_status("accepted");
        friendRequestRepository.save(request);
    }

    public void rejectRequest(Long senderId, Long receiverId)
    {
        FriendRequest request = friendRequestRepository.findRequest(senderId,receiverId);
        request.setFriendReq_status("rejected");
        friendRequestRepository.save(request);
    }

    public List <FriendRequest> getPendingRequestsReceived(Long receiverId)
    {
        return friendRequestRepository.findbyReceiverId(receiverId).stream().filter(f -> f.getFriendReq_status().equals("pending")).collect(Collectors.toList());
    }

    public List <FriendRequest> getPendingRequestsSent(Long receiverId)
    {
        return friendRequestRepository.findbySenderId(receiverId).stream().filter(f -> f.getFriendReq_status().equals("pending")).collect(Collectors.toList());
    }

    public List <FriendRequest> getFriends(Long userId)
    {
        return friendRequestRepository.getFriends(userId);
    }

    public void deleteFriendRequest(Long senderId, Long receiverId) {
        friendRequestRepository.deleteFriendRequest(senderId, receiverId);
    }


}
