package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserAccountService {

    private UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }
/*
    public List<Customer> findAll(){}
    public Customer findById(Long id){}
    public Customer findByEmail(String email){}
    public int getAge(Long id){}
    public boolean existsByEmail(){}*/


}
