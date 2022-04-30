package com.rental_backend.repository;

import com.rental_backend.entity.Customer;
import com.rental_backend.entity.MovieRequest;
import com.rental_backend.entity.RentedMovie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    List<Customer> findAll();


}
