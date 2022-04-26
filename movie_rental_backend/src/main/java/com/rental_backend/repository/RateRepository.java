package com.rental_backend.repository;

import com.rental_backend.entity.Customer;
import com.rental_backend.entity.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends CrudRepository<Rate,Long> {
}
