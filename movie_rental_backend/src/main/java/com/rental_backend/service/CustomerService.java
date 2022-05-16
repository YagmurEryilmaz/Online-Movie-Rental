package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.exception.CustomerNotFoundException;
import com.rental_backend.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Customer addUser(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteUserByEmail(String email) {
        if (customerRepository.existsCustomerByEmail(email)) {
            customerRepository.deleteUserByEmail(email);
        }
        else {
            throw new CustomerNotFoundException("Customer with email " + email + " does not exist.");
        }

    }
    public void deleteUserByUId(Long uId) {
        if (customerRepository.existsCustomerByuId(uId)) {
            customerRepository.deleteUserByUId(uId);
        }
        else {
            throw new CustomerNotFoundException("Customer with id " + uId + " does not exist.");
        }

    }

    public void updateUserByUId(Long uId, String email)
    {
        customerRepository.updateUserByUId(uId,email);
    }
    public void updateBalance(Long uId, Double amount)
    {
        customerRepository.updateBalance(uId,amount);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
    public List<String> findAllEmails() {
        return customerRepository.findAllEmails();
    }

    public Customer findById(Long id) {
        return customerRepository.findByUId(id);
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    public Customer addRented(Long c_id, RentedMovie rm){
        Customer customer = customerRepository.findByUId(c_id);
        return customerRepository.save(customer);
    }

    // methods related to Rented Movie repo
    public List<RentedMovie> findAllRentedMovies() {
        return rentedMovieRepository.findAll();
    }

    // methods related to Friend Request repo

    public int getPendingFriendRequestCount(Long receiverId){ return friendRequestRepository.findNumOfPendingRequests(receiverId);}
    public int getReceivedFriendRequestCount(Long receiverId){ return friendRequestRepository.findNumOfReceivedRequests(receiverId);}
    public int getSentFriendRequestCount(Long senderId){ return friendRequestRepository.findNumOfSentRequests(senderId);}
    public int getFriendCount(Long receiverId, Long senderId){ return friendRequestRepository.findNumOfFriends(receiverId, senderId);}

    public List<String> getFriendEmail(Long uId) {
        return customerRepository.getFriendEmail(uId);
    }

    // methods related to Gift repo
    public int getReceivedGiftCount(Long receiverId){ return giftRepository.findNumOfReceivedGifts(receiverId);}

    // methods that are come from customer entity

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
