package com.rental_backend.repository;

import com.rental_backend.entity.MovieRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRequestRepository extends CrudRepository<MovieRequest, Long> {
    List<MovieRequest> findAll();
    //Optional<T> findById(Long userId);
    boolean existsById(Long movieReqId); // id direk vermek gerekebilir buna bak

    @Modifying
    @Transactional
    @Query("delete from MovieRequest m where m.movieReqId= :id")
    void deleteMovieRequest(@Param("id") Long id);

}
