package com.rental_backend.repository;
import com.rental_backend.entity.Rate;
import com.rental_backend.entity.Review;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.sql.Insert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.Values;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review,Long> {
    List<Review> findAll();
    //@SQLInsert(sql = "INSERT INTO review(comment) value (comment = :comment)")
    //List<Review> createComment(@Param("comment") String comment);
}
