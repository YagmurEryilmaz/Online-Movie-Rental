package com.rental_backend.repository;
import com.rental_backend.entity.SubtitleLang;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtitleLangRepository extends CrudRepository<SubtitleLang,Long> {
}
