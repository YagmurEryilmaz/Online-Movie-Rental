package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieRequestService {

    private MovieRequestRepository movieRequestRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public MovieRequestService(MovieRequestRepository movieRequestRepository, CustomerRepository customerRepository) {
        this.movieRequestRepository = movieRequestRepository;
        this.customerRepository = customerRepository;
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
