package com.rental_backend.repository;
import com.rental_backend.entity.RentedMovie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface RentedMovieRepository extends CrudRepository<RentedMovie,Long> {

    Date now = Date.valueOf(LocalDate.now());

    List<RentedMovie> findAll();

    @Query
    List<RentedMovie> getCurrentlyRented(@Param("userId") String userId);
}
