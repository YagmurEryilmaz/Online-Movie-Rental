package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserAccountService {

    public List<Customer> findAll(){}
    public Customer findById(Long id){}
    public Customer findByEmail(String email){}
    public int getAge(Long id){}
    public boolean existsByEmail(){}


}
