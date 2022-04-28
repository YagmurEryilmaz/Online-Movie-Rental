package com.rental_backend.repository;

import com.rental_backend.entity.Customer;
import com.rental_backend.entity.FriendRequest;
import com.rental_backend.entity.Suggestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest,Long> {
    @Query("select fr.receiver.name from FriendRequest fr, Customer c where fr.sender.uId = c.uId and fr.sender.uId = :senderId ")
    List<FriendRequest> findbySenderId(@Param("senderId") String senderId);

    @Query("select fr.sender.name from FriendRequest fr, Customer c where fr.receiver.uId = c.uId and fr.receiver.uId = :receiverId ")
    List<FriendRequest> findbyReceiverId(@Param("receiverId") String receiverId);

    @Query("select count(fr.receiver.receivedRequests) from FriendRequest fr, Customer c where fr.receiver.uId = c.uId and fr.receiver.uId = :receiverId")
    List<FriendRequest> findNumOfReceivedRequests(@Param("receiverId") String receiverId);

}
