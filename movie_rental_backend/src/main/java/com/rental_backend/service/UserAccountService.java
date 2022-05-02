package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import java.security.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static java.lang.Long.decode;

@Service
public class UserAccountService {

    private UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }


    public UserAccount addUser(UserAccount user) {
        return userAccountRepository.save(user);
    }


    public UserAccount removeUser(UserAccount user) {
        userAccountRepository.deleteById(user.getUId());
        return user;
    }

    public List<UserAccount> findAll(){

        return userAccountRepository.findAll();
    }

    public UserAccount findById(Long id){

        return userAccountRepository.findByUId(id);
    }
    public UserAccount findByEmail(String email){

        return userAccountRepository.findUserAccountByEmail(email);
    }

    public int getAge(Long id){

        Date birthday = userAccountRepository.findByUId(id).getBirthday();
        int today = Calendar.getInstance().get(Calendar.YEAR);
        int age = today - birthday.getYear();
        return age;
    }
    public boolean existsByEmail(String email){

        return userAccountRepository.existsUserAccountByEmail(email);
    }

    public boolean login(String email, String password) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Optional<UserAccount> user = Optional.ofNullable(userAccountRepository.findUserAccountByEmail(email));
        if (user.isPresent()) {
            UserAccount u = user.get();
            return decode(u.getPassword()).equals(password);
        }
        return false;
    }



}
