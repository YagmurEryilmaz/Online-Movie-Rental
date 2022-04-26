package com.rental_backend.repository;
import com.rental_backend.entity.SubtitleRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtitleRequestRepository extends CrudRepository<SubtitleRequest,Long> {
}
