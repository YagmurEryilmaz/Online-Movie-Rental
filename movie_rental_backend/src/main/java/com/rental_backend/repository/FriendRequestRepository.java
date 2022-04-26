package com.rental_backend.repository;

import com.rental_backend.entity.Customer;
import com.rental_backend.entity.FriendRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest,Long> {
}
