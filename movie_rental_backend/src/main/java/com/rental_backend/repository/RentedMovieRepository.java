package com.rental_backend.repository;
import com.rental_backend.entity.Customer;
import com.rental_backend.entity.Movie;
import com.rental_backend.entity.RentedMovie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.LongSummaryStatistics;


@Repository
public interface RentedMovieRepository extends CrudRepository<RentedMovie,Long> {

    Date now = Date.valueOf(LocalDate.now());
    Customer customer = new Customer();

    List<RentedMovie> findAll();

   /* @Query("select r.movie from RentedMovie r where r.customer = :userId and r.expDate > :now and r.customer ")
    List<Movie> getCurrentlyRented(@Param("userId") Long userId);

    @Query("select r.movie from RentedMovie r where rexpDate <= :now")
    List<Movie> getPreviouslyRented(@Param("userId") Long userId);*/
}
