package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendRequestService {

    private FriendRequestRepository friendRequestRepository;
    private CustomerService customerService;
    private UserAccountService userAccountService;

    @Autowired
    public FriendRequestService(FriendRequestRepository friendRequestRepository, CustomerService customerService, UserAccountService userAccountService) {
        this.friendRequestRepository = friendRequestRepository;
        this.customerService = customerService;
        this.userAccountService=userAccountService;
    }
    public List<FriendRequest> findByReceiverId(Long receiverId) {
        return friendRequestRepository.findbyReceiverId(receiverId);
    }
    public List<FriendRequest> findBySenderId(Long senderId) {
        return friendRequestRepository.findbySenderId(senderId);
    }
    public FriendRequest addFriendRequest(String senderEmail, String receiverEmail) {

        UserAccount sender = userAccountService.findByEmail(senderEmail);
        UserAccount receiver =  userAccountService.findByEmail(receiverEmail);

        FriendRequest.PrimaryKey key = new FriendRequest.PrimaryKey(sender.getUId(), receiver.getUId());

        FriendRequest fr = FriendRequest.builder()
                .primaryKey(key)
                .receiver(receiver)
                .sender(sender)
                .friendReq_status("pending")
                .build();

        return friendRequestRepository.save(fr);
    }

    public void acceptRequest(String senderEmail, Long receiverId)
    {
        Customer sender = customerService.findByEmail(senderEmail);

        FriendRequest request = friendRequestRepository.findRequest(receiverId,sender.getUId());
        request.setFriendReq_status("accepted");
        friendRequestRepository.save(request);
    }

    public void rejectRequest(String senderEmail, Long receiverId)
    {
        Customer sender = customerService.findByEmail(senderEmail);

        FriendRequest request = friendRequestRepository.findRequest(receiverId,sender.getUId());
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
