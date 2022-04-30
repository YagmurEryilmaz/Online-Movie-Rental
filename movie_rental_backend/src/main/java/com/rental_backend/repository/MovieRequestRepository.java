package com.rental_backend.repository;

import com.rental_backend.entity.MovieRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRequestRepository extends CrudRepository<MovieRequest, Long> {
    List<MovieRequest> findAll();
    //Optional<T> findById(Long userId);
    boolean existsById(Long movieReqId); // id direk vermek gerekebilir buna bak

}
