package com.rental_backend.repository;

import com.rental_backend.entity.Customer;
import com.rental_backend.entity.MovieRequest;
import com.rental_backend.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRequestRepository extends CrudRepository<MovieRequest,Long> {
    List<MovieRequest> findAll();
    boolean existsById(Long movieReqId);

}
