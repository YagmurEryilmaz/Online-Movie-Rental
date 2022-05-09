package com.rental_backend.repository;
import com.rental_backend.entity.Movie;
import com.rental_backend.entity.MovieRequest;
import com.rental_backend.entity.SubtitleLang;
import com.rental_backend.entity.SubtitleRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtitleRequestRepository extends CrudRepository<SubtitleRequest,Long> {
   // List<SubtitleRequest> findByMovie(Movie mId);
    List<SubtitleRequest> findAll();
}
