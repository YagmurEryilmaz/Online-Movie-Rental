package com.rental_backend.repository;
import com.rental_backend.entity.SubtitleRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubtitleRequestRepository extends CrudRepository<SubtitleRequest,Long> {
    List<SubtitleRequest> findAll();
    @Modifying
    @Transactional
    @Query("delete from SubtitleRequest s where s.subtitleReqId= :id")
    void deleteSubtitleRequest(@Param("id") Long id);
}
