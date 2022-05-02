package com.rental_backend.controller;

import com.rental_backend.dto.LoginRequest;
import com.rental_backend.dto.MessageResponse;
import com.rental_backend.dto.SignupRequest;
import com.rental_backend.dto.UserResponse;
import com.rental_backend.entity.UserAccount;
import com.rental_backend.service.UserAccountService;
import com.rental_backend.entity.Customer;
import com.rental_backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import javax.crypto.*;
import java.io.IOException;
import java.security.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserAccountService userAccountService;
    private final CustomerService customerService;
    //private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    /*
    @GetMapping("/signin")
    public UserResponse signIn ( @RequestBody LoginRequest loginRequest)  {

        if (userAccountService.existsByEmail(loginRequest.getEmail()))
        {
            String providedPassword = loginRequest.getPassword();
            String requiredPassword = userAccountService.findByEmail(loginRequest.getEmail()).getPassword();
            if (providedPassword.equals(requiredPassword)) {
                UserAccount user = userAccountService.findByEmail(loginRequest.getEmail());
                /*
                //employee not included for now
                if(user.getRole().equals("customer")) {
                    Customer customer = (Customer) user;
                    return UserResponse.builder()
                            .success(true)
                            .id(customer.getUId())
                            .email(customer.getEmail())
                            .role(customer.getRole())
                            .build();
                }
                return UserResponse.builder()
                        .success(true)
                        .id(user.getUId())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build();

            }
        }
        return UserResponse.builder().success(false).build();
    } */
    @GetMapping("/signin/{email}/{password}")
    public boolean loginInstructor(@PathVariable String email, @PathVariable String password) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return userAccountService.login(email,password);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {

        if (userAccountService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        UserAccount user = UserAccount
                .builder()
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .role(signUpRequest.getRole())
                .name(signUpRequest.getName())
                .birthday(signUpRequest.getBirthday())
                .build();
        if(user.getRole().equals("customer")) {
            Customer customer = Customer
                    .builder()
                    .email(signUpRequest.getEmail())
                    .password(signUpRequest.getPassword())
                    .role(signUpRequest.getRole())
                    .name(signUpRequest.getName())
                    .birthday(signUpRequest.getBirthday())
                    .build();
            //customerService.addUser((Customer)user);
            customerService.addUser(customer);
        }else {
            userAccountService.addUser(user);
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}
