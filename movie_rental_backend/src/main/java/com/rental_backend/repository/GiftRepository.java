package com.rental_backend.repository;

import com.rental_backend.entity.Gift;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface GiftRepository extends CrudRepository<Gift,Long> {

    @Query("select g from Gift g, Customer c where g.senderCustomer.uId = c.uId and g.senderCustomer.uId = :senderId ")
    List<Gift> findbySenderId(@Param("senderId") Long senderId);

    @Query("select g from Gift g, Customer c where g.receiverCustomer.uId = c.uId and g.receiverCustomer.uId = :receiverId ")
    List<Gift> findbyReceiverId(@Param("receiverId") Long receiverId);

    @Query("select count(g.receiverCustomer.receivedGifts) from Gift g, Customer c where g.receiverCustomer.uId = c.uId and g.receiverCustomer.uId = :receiverId")
    int findNumOfReceivedGifts(@Param("receiverId") Long receiverId);
}
