package com.rental_backend.repository;


import com.rental_backend.entity.Movie;
import com.rental_backend.entity.UserAccount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.ForeignKey;
import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends CrudRepository<Movie,Long>{


    List<Movie> findAll();
    List<Movie> findByTitle(String title);
    List <Movie> findByGenre(String genre);
    List <Movie> findByProductionYear(int productionYear);
    List<Movie> findBySubtitleLang(String subtitleLang);
    boolean existsByTitleAndDirectorName(String title, String directorName);
    Movie findById(long mId);

    @Query("select m from Movie m where m.mId = :id")
    Movie findMovieById(@Param("id") Long id);

    @Query("select m.title from Movie m " +
            "where lower(m.title) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(m.directorName) like lower(concat('%', :searchTerm, '%'))")
    List<Movie> search(@Param("searchTerm") String searchTerm);

    @Transactional
    @Modifying
    @Query("delete from Movie m where m.mId= :mId  ")
    void deleteMovie(@Param("mId") Long mId);

    @Modifying
    @Transactional
    @Query("update Movie m set m.price=:price where m.mId=:mId ")
    void updateMoviePrice(@Param("mId") Long mId, @Param("price") double price);

    @Modifying
    @Transactional
    @Query("update Movie m set m.trailerUrl=:trailerUrl where m.mId=:mId ")
    void updateTrailer(@Param("mId") Long mId, @Param("trailerUrl") String trailerUrl);






}
