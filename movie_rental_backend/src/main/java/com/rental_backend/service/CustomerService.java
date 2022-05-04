package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.exception.CustomerNotFoundException;
import com.rental_backend.exception.MovieRequestNotFoundException;
import com.rental_backend.exception.RentedMovieNotFoundException;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private RentedMovieRepository rentedMovieRepository;
    private FriendRequestRepository friendRequestRepository;
    private GiftRepository giftRepository;
    private MovieRequestRepository movieRequestRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, RentedMovieRepository rentedMovieRepository,FriendRequestRepository friendRequestRepository,GiftRepository giftRepository,MovieRequestRepository movieRequestRepository) {
        this.customerRepository = customerRepository;
        this.rentedMovieRepository = rentedMovieRepository;
        this.friendRequestRepository= friendRequestRepository;
        this.giftRepository=giftRepository;
        this.movieRequestRepository= movieRequestRepository;
    }
    public Customer addUser(Customer customer) {
        return customerRepository.save(customer);
    }
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer removeUser(Customer customer) {
        customerRepository.deleteById(customer.getUId());
        return customer;
    }

    public Customer findById(Long id) {
        return customerRepository.findByUId(id);
    }

    public Customer addRented(Long c_id, RentedMovie rm){
        Customer customer = customerRepository.findByUId(c_id);
        customer.addRentedMovie(rm);
        return customerRepository.save(customer);
    }

    // methods related to Rented Movie repo
    public List<RentedMovie> findAllRentedMovies() {
        return rentedMovieRepository.findAll();
    }

    public List<Movie> getCurrentlyRentedMovies(Long userId) {
        Date now = Date.valueOf(LocalDate.now());
        return rentedMovieRepository.getCurrentlyRented(userId, now);
    }

    public List<Movie> getPreviouslyRentedMovies(Long userId) {
        Date now = Date.valueOf(LocalDate.now());
        return rentedMovieRepository.getPreviouslyRented(userId, now);
    }

    // methods related to Friend Request repo

    public int getPendingFriendRequestCount(Long receiverId){ return friendRequestRepository.findNumOfPendingRequests(receiverId);}
    public int getReceivedFriendRequestCount(Long receiverId){ return friendRequestRepository.findNumOfReceivedRequests(receiverId);}
    public int getSentFriendRequestCount(Long senderId){ return friendRequestRepository.findNumOfSentRequests(senderId);}
    public int getFriendCount(Long receiverId, Long senderId){ return friendRequestRepository.findNumOfFriends(receiverId, senderId);}

    // methods related to Gift repo
    public int getReceivedGiftCount(Long receiverId){ return giftRepository.findNumOfReceivedGifts(receiverId);}

    // methods that are come from customer entity

    /*public Customer addMovieRequest(MovieRequest movieRequest, Long userId) throws MovieRequestNotFoundException, CustomerNotFoundException {
        if (customerRepository.existsById(userId)) {
            Customer customer = customerRepository.findById(userId).get();
            if (movieRequestRepository.existsById(movieRequest.getMovieReqId())) {
                customer.addMovieRequest(movieRequestRepository.findById(movieRequest.getMovieReqId()));
                return customerRepository.save(customer);
            }
            else {
                throw new MovieRequestNotFoundException("Movie request with name " + movieRequest.getMovieName() + " does not exist.");
            }
        }
        else {
            throw new CustomerNotFoundException("Customer with id " + userId + " does not exist.");
        }
    }
    /*public Customer addRentedMovie(RentedMovie rentedMovie, Long userId) throws RentedMovieNotFoundException, CustomerNotFoundException {
        if (customerRepository.existsById(userId)) {
            Customer customer = customerRepository.findById(userId).get();
            if (rentedMovieRepository.existsById(rentedMovie)) {
                customer.addRentedMovie(rentedMovieRepository.findByUserId(userId).iterator().next(), userId);
                return customerRepository.save(customer);
            }
            else {
                throw new RentedMovieNotFoundException("Rented movie with name " + rentedMovie.getMovie() + " does not exist.");
            }
        }
        else {
            throw new CustomerNotFoundException("Customer with id " + userId + " does not exist.");
        }
    }*/






}
