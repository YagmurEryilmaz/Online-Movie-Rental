package com.rental_backend.repository;

import com.rental_backend.entity.Customer;
import com.rental_backend.entity.Gift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftRepository extends CrudRepository<Gift,Long> {
}
