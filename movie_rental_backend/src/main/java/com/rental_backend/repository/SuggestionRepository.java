package com.rental_backend.repository;
import com.rental_backend.entity.Suggestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestionRepository extends CrudRepository<Suggestion,Long> {
    List<Suggestion> findAll();
    @Query("select s from Suggestion s, Customer c where s.primaryKey.msender_id = c.uId and s.primaryKey.msender_id = :senderId ")
    List<Suggestion> findSuggestionBySenderId(@Param("senderId") Long senderId);

    @Query("select s from Suggestion s, Customer c where s.primaryKey.mreceiver_id = c.uId and s.primaryKey.mreceiver_id = :receiverId ")
    List<Suggestion> findSuggestionByReceiverId(@Param("receiverId") Long receiverId);
}
