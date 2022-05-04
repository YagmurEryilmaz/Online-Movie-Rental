package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MovieRequestService {

    private MovieRequestRepository movieRequestRepository;

    @Autowired
    public MovieRequestService(MovieRequestRepository movieRequestRepository) {
        this.movieRequestRepository = movieRequestRepository;
    }
    public List<MovieRequest> getAllMovieRequests(){
        return movieRequestRepository.findAll();
    }
    //public List<Movie> requestMovie(@RequestBody )
}
