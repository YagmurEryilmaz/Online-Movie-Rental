package com.rental_backend.repository;

import com.rental_backend.entity.Customer;
import com.rental_backend.entity.FriendRequest;
import com.rental_backend.entity.Suggestion;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest,Long> {

    List<FriendRequest> findAll();

    @Query("select fr.receiver.name from FriendRequest fr, Customer c where fr.sender.uId = c.uId and fr.sender.uId = :senderId ")
    List<FriendRequest> findbySenderId(@Param("senderId") Long senderId);

    @Query("select fr.sender.name from FriendRequest fr, Customer c where fr.receiver.uId = c.uId and fr.receiver.uId = :receiverId ")
    List<FriendRequest> findbyReceiverId(@Param("receiverId") Long receiverId);

    @Query("select count(fr.receiver.receivedRequests) from FriendRequest fr, Customer c where (fr.friendReq_status = 'accepted' and fr.receiver.uId = c.uId and fr.receiver.uId = :receiverId) or (fr.friendReq_status = 'accepted' and fr.sender.uId = :senderId and fr.sender.uId = c.uId ) ")
    int findNumOfFriends(@Param("receiverId")  Long receiverId, @Param("senderId")Long senderId);

    @Query("select count(fr.receiver.receivedRequests) from FriendRequest fr, Customer c where fr.friendReq_status = 'waiting' and fr.receiver.uId = :receiverId ")
    int findNumOfPendingRequests(@Param("receiverId") Long receiverId);

    @Query("select count(fr.receiver.receivedRequests) from FriendRequest fr, Customer c where fr.receiver.uId = c.uId and fr.receiver.uId = :receiverId")
    int findNumOfReceivedRequests(@Param("receiverId") Long receiverId);

    @Query("select count(fr.sender.sentRequests) from FriendRequest fr, Customer c where fr.sender.uId = c.uId and fr.sender.uId = :senderId")
    int findNumOfSendedRequests(@Param("senderId") Long senderId);

}
