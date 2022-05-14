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


    List<RentedMovie> findAll();

    @Query("select r.movie.title from RentedMovie r where r.pk.uId = :cId and r.pk.mId = :mId")
    List <RentedMovie> findRentedMovieByCustomerAndMovie(@Param("mId") Long mId, @Param("cId") Long cId);

    @Query("select m.movie.title from RentedMovie m where m.pk = :id")
    RentedMovie findRentedMovieById(@Param("id") Long id);

   @Query("select r from RentedMovie r where r.pk.uId = :userId and r.expDate > :date ")
    List<RentedMovie> getCurrentlyRented(@Param("userId") Long userId, @Param("date") Date date);

    @Query("select r from RentedMovie r where r.pk.uId= :userId and r.expDate <= :date")
    List<RentedMovie> getPreviouslyRented(@Param("userId") Long userId , @Param("date") Date date);

}
