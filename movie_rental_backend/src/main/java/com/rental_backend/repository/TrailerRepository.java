package com.rental_backend.repository;
import com.rental_backend.entity.Trailer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrailerRepository extends CrudRepository<Trailer,Long> {
}
