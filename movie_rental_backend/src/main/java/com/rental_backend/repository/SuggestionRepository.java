package com.rental_backend.repository;
import com.rental_backend.entity.Rate;
import com.rental_backend.entity.Suggestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionRepository extends CrudRepository<Suggestion,Long> {
    List<Suggestion> findAll();
    /*
    @Query("select s.movie.title from Suggestion s, Movie m where s.movie.mId = m.mId and m.title = :smovieId ")
    List<Suggestion> findSuggestedMovie(@Param("smovieId") String smovieId); */
}
