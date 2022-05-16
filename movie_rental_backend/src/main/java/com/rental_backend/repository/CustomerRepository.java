package com.rental_backend.repository;

import com.rental_backend.entity.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    List<Customer> findAll();
    boolean existsCustomerByEmail(String email);
    boolean existsCustomerByuId(Long uId);

    @Query("select c from Customer c where c.uId= :userId")
    Customer findByUId(@Param("userId") Long userId);

    @Query("select c from Customer c where c.email= :email")
    Customer findByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("delete from Customer c where c.email= :email")
    void deleteUserByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("delete from Customer c where c.uId= :uId")
    void deleteUserByUId(@Param("uId") Long uId);

    @Modifying
    @Transactional
    @Query("update Customer c set c.email=:email where c.uId=:uId ")
    void updateUserByUId(@Param("uId") Long uId, @Param("email") String email);

    @Modifying
    @Transactional
    @Query("update Customer c set c.balance=:balance where c.uId=:uId ")
    void updateBalance(@Param("uId") Long uId, @Param("balance") Double balance);

    @Query("select c.email from Customer c")
    List<String> findAllEmails();

    @Query("select c.email from FriendRequest fr, Customer c where c.uId= fr.primaryKey.receiver_id and fr.friendReq_status='accepted' and (fr.primaryKey.sender_id = :userId or fr.primaryKey.receiver_id = :userId)")
    List<String> getFriendEmail(@Param("userId") Long userId);

}
