package com.rental_backend.service;

import com.rental_backend.entity.UserAccount;
import com.rental_backend.exception.CustomerNotFoundException;
import com.rental_backend.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;


@Service
public class UserAccountService {
    private UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }
    public UserAccount deleteAndReturnUser(UserAccount user) {
        if (userAccountRepository.existsUserAccountByuId(user.getUId())) {
            userAccountRepository.deleteUserByUId(user.getUId());
        }
        else {
            throw new CustomerNotFoundException("Customer with id " + user.getUId() + " does not exist.");
        }
        return user;
    }

    public void deleteUserByUId(Long uId) {
        if (userAccountRepository.existsUserAccountByuId(uId)) {
            userAccountRepository.deleteUserByUId(uId);
        }
        else {
            throw new CustomerNotFoundException("Customer with id " + uId + " does not exist.");
        }

    }
    public List<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

    public UserAccount findById(Long id) {
        return userAccountRepository.findByUId(id);
    }

    public UserAccount findByEmail(String email) {
        return userAccountRepository.findUserAccountByEmail(email);
    }

    public int getAge(Long id) {
        Date birthday = userAccountRepository.findByUId(id).getBirthday();
        int today = Calendar.getInstance().get(Calendar.YEAR);
        int age = today - birthday.getYear();
        return age;
    }

    public boolean existsByEmail(String email) {
        return userAccountRepository.existsUserAccountByEmail(email);
    }
}