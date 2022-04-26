package com.rental_backend.repository;


import com.rental_backend.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie,Long>{


    List<Movie> findAll();

    @Query("select m from Movie m " +
            "where lower(m.title) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(m.director_name) like lower(concat('%', :searchTerm, '%'))")
    List<Movie> search(@Param("searchTerm") String searchTerm);

    


}