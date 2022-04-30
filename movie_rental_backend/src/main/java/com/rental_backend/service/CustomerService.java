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
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    // methods related to Rented Movie repo
    public List<RentedMovie> findAllRentedMovies() {
        return rentedMovieRepository.findAll();
    }

    public List<RentedMovie> getCurrentlyRentedMovies(Long userId) {
        return rentedMovieRepository.getCurrentlyRented(userId);
    }

    public List<RentedMovie> getPreviouslyRentedMovies(Long userId) {
        return rentedMovieRepository.getPreviouslyRented(userId);
    }

    // methods related to Friend Request repo

    public int getPendingFriendRequestCount(Long receiverId){ return friendRequestRepository.findNumOfPendingRequests(receiverId);}
    public int getReceivedFriendRequestCount(Long receiverId){ return friendRequestRepository.findNumOfReceivedRequests(receiverId);}
    public int getSendedFriendRequestCount(Long senderId){ return friendRequestRepository.findNumOfSendedRequests(senderId);}
    public int getFriendCount(Long receiverId, Long senderId){ return friendRequestRepository.findNumOfFriends(receiverId, senderId);}

    // methods related to Gift repo
    public int getReceivedGiftCount(Long receiverId){ return giftRepository.findNumOfReceivedGifts(receiverId);}

    // methods that are come from customer entity

    /*public Customer addMovieRequest(MovieRequest movieRequest, Long userId) throws MovieRequestNotFoundException, CustomerNotFoundException {
        if (customerRepository.existsById(userId)) {
            Customer customer = customerRepository.findById(userId).get();
            if (movieRequestRepository.existsById(movieRequest.getMovieReqId())) {
                customer.addMovieRequest(movieRequestRepository.findById(userId), userId);
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
