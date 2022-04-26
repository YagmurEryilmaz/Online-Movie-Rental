package com.rental_backend.repository;

import com.rental_backend.entity.MovieLang;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieLangRepository extends CrudRepository<MovieLang,Long> {
}
