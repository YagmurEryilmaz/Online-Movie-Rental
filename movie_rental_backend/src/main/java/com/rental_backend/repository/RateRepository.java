package com.rental_backend.repository;

import com.rental_backend.entity.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends CrudRepository<Rate,Long> {
    List<Rate> findAll();

    @Query("select r from Rate r where r.pk = :id")
    Rate findRateById(@Param("id") Long id);

    @Query("select avg(r.point) as avgPoint from Rate r, Movie m where m.mId= r.movie.mId and m.mId= :movieId group by m.mId")
    Float findAvgPointPerMovie(@Param("movieId") Long movieId);

    @Query("select r from Rate r where r.pk.mId = :movieId")
    List <Rate> getAllByMovie(@Param("movieId") Long movieId);
}
