package com.rental_backend.repository;

import com.rental_backend.entity.Customer;
import com.rental_backend.entity.MovieRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {


    boolean existsByEmail(String email);
    List<Customer> findAll();


}
