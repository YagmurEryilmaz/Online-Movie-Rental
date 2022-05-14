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
    private CustomerRepository customerRepository;

    @Autowired
    public MovieRequestService(MovieRequestRepository movieRequestRepository) {
        this.movieRequestRepository = movieRequestRepository;
    }
    public List<MovieRequest> getAllMovieRequests(){
        return movieRequestRepository.findAll();
    }

    public MovieRequest addRequest(Long cId, String movieName, String drectorName, int productionYear ) {

        MovieRequest m = MovieRequest.builder()
                                    .movieName(movieName)
                                    .directorName(drectorName)
                                    .movieProductionYear(productionYear)
                                    .movieReqStatus("pending")
                                    .customer(customerRepository.findByUId(cId))
                                    .build();
        return movieRequestRepository.save(m);
    }
    //public List<Movie> requestMovie(@RequestBody )
}
