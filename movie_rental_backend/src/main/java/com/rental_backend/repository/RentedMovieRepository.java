package com.rental_backend.repository;
import com.rental_backend.entity.RentedMovie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentedMovieRepository extends CrudRepository<RentedMovie,Long> {
}
