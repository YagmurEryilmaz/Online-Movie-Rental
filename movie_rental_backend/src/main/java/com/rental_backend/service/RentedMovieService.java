package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public RentedMovie findMovieById(Long id) {
        return rentedMovieRepository.findRentedMovieByMovieId(id);
    }

    public void rentMovie( Long c_id, Long m_id, Date expDate )
    {
        if (isRentedCurrently(c_id, m_id))
            throw new RuntimeException("currently rented");
        else {
            RentedMovie.PrimaryKey key = new RentedMovie.PrimaryKey(c_id, m_id);
            RentedMovie rentedMovie = RentedMovie.builder()
                    .pk(key)
                    .customer(customerService.findById(c_id))
                    .movie(movieService.findMovieById(m_id))
                    .expDate(expDate)
                    .build();
            rentedMovieRepository.save(rentedMovie);
        }

    }

    public List<RentedMovie> getCurrentlyRented( Long userId){
        Date now = Date.valueOf(LocalDate.now());
        return rentedMovieRepository.getCurrentlyRented(userId, now);
    }

    public List<RentedMovie> getPreviouslyRented( Long userId){
        Date now = Date.valueOf(LocalDate.now());
        return rentedMovieRepository.getPreviouslyRented(userId, now);
    }

    public Boolean isRentedCurrently(Long c_id, Long m_id){
        List <Movie> current = new ArrayList<>();
        getCurrentlyRented(c_id).forEach((rm)-> current.add(rm.getMovie()));
        Movie movie = movieService.findMovieById(m_id);
        return current.contains(movie);
    }

    public Boolean isRentedPreviously(Long c_id, Long m_id){
        List <Movie> prev = new ArrayList<>();
        getPreviouslyRented(c_id).forEach((rm)-> prev.add(rm.getMovie()));
        Movie movie = movieService.findMovieById(m_id);
        return prev.contains(movie);
    }




}
