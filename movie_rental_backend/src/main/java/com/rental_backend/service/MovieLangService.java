package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieLangService {

    private MovieLangRepository movieLangRepository;

    @Autowired
    public MovieLangService(MovieLangRepository movieLangRepository) {
        this.movieLangRepository = movieLangRepository;
    }

    public MovieLang addLanguage(MovieLang movieLang) {
        return movieLangRepository.save(movieLang);
    }
}
