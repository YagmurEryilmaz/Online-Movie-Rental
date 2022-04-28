package com.rental_backend.repository;

import com.rental_backend.entity.Customer;
import com.rental_backend.entity.FriendRequest;
import com.rental_backend.entity.Gift;
import com.rental_backend.entity.Suggestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftRepository extends CrudRepository<Gift,Long> {
    @Query("select g.movie.title from Gift g, Customer c where g.senderCustomer.uId = c.uId and g.senderCustomer.uId = :senderId ")
    List<Gift> findbySenderId(@Param("senderId") String senderId);

    @Query("select g.movie.title from Gift g, Customer c where g.receiverCustomer.uId = c.uId and g.receiverCustomer.uId = :receiverId ")
    List<Gift> findbyreceiverId(@Param("receiverId") String receiverId);

    @Query("select count(g.receiverCustomer.receivedGifts) from Gift g, Customer c where g.receiverCustomer.uId = c.uId and g.receiverCustomer.uId = :receiverId")
    List<Gift> findNumOfReceivedGifts(@Param("receiverId") String receiverId);
}
