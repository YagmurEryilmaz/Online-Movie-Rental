package com.rental_backend.repository;
import com.rental_backend.entity.Rate;
import com.rental_backend.entity.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review,Long> {
    List<Review> findAll();

    @Query("select avg(r.point) as avgPoint from Review r, Movie m group by m")
    List<Review> findAvgRatePerMovie(@Param("searchTerm") Long movieId);
}
