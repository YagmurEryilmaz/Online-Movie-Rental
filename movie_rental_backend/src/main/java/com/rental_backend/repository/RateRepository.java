package com.rental_backend.repository;

import com.rental_backend.entity.Customer;
import com.rental_backend.entity.Movie;
import com.rental_backend.entity.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends CrudRepository<Rate,Long> {

}
