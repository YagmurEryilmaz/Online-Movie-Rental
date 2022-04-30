package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FriendRequestService {

    public List<Customer> getFriends( Long id){}
    public List<Customer> getFriendRequests(Long id){}
    public List<Customer> getPendingRequests(Long id){}
    public List<FriendRequest> findByReceiverId(Long receiverId){}
    public List <FriendRequest> findBySenderId(Long senderId){}


}
