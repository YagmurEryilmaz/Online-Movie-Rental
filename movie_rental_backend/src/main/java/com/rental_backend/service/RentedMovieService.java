package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class RentedMovieService {

    private final RentedMovieRepository rentedMovieRepository;
    private final CustomerService customerService;
    private final MovieService movieService;
    private final PaymentService paymentService;


    @Autowired
    public RentedMovieService(RentedMovieRepository rentedMovieRepository, MovieService movieService,
                              CustomerService customerService, PaymentService paymentService) {
        this.rentedMovieRepository = rentedMovieRepository;
        this.movieService = movieService;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    public void rentMovie( Long c_id, Long m_id, Long p_id )
    {
        RentedMovie.PrimaryKey key = new RentedMovie.PrimaryKey(c_id, m_id, p_id);
        RentedMovie rentedMovie = RentedMovie.builder().pk(key).build();
        rentedMovieRepository.save(rentedMovie);
        customerService.addRented(c_id, rentedMovie);
        movieService.addRented(m_id,rentedMovie);

    }

    public List<RentedMovie> getCurrentlyRented( Long userId){
        Date now = Date.valueOf(LocalDate.now());
        return rentedMovieRepository.getCurrentlyRented(userId, now);
    }

    public List<RentedMovie> getPreviouslyRented( Long userId){
        Date now = Date.valueOf(LocalDate.now());
        return rentedMovieRepository.getPreviouslyRented(userId, now);
    }




}
