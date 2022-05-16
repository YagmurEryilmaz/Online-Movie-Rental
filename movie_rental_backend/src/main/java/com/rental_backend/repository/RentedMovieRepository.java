package com.rental_backend.repository;
import com.rental_backend.entity.RentedMovie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public interface RentedMovieRepository extends CrudRepository<RentedMovie,Long> {
    List<RentedMovie> findAll();

    @Query("select r from RentedMovie r where r.pk.mId = :mId")
    RentedMovie findRentedMovieByMovieId(@Param("mId") Long mId);

    @Query("select r from RentedMovie r where r.pk.uId = :userId and r.expDate > :date ")
    List<RentedMovie> getCurrentlyRented(@Param("userId") Long userId, @Param("date") Date date);

    @Query("select r from RentedMovie r where r.pk.uId= :userId and r.expDate <= :date")
    List<RentedMovie> getPreviouslyRented(@Param("userId") Long userId , @Param("date") Date date);

}
