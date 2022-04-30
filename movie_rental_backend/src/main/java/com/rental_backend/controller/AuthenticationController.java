package com.rental_backend.controller;

import com.rental_backend.dto.LoginRequest;
import com.rental_backend.dto.MessageResponse;
import com.rental_backend.dto.SignupRequest;
import com.rental_backend.dto.UserResponse;
import com.rental_backend.entity.UserAccount;
import com.rental_backend.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserAccountService userAccountService;
    //private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/signin")
    public UserResponse signIn ( @RequestBody LoginRequest loginRequest)  {

        System.out.println("email " + loginRequest.getEmail());
        if (userAccountService.existsByEmail(loginRequest.getEmail()))
        {
            String providedPassword = loginRequest.getPassword();
            String requiredPassword = userAccountService.findByEmail(loginRequest.getEmail()).getPassword();
            System.out.println("required " + requiredPassword + " provided " + providedPassword);
            if (providedPassword == requiredPassword) {

                UserAccount user = userAccountService.findByEmail(loginRequest.getEmail());
                return UserResponse.builder()
                        .success(true)
                        .id(user.getUId())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build();
            }
        }
        else
            System.out.println("no");
        return UserResponse.builder().success(false).build();
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
                .build();
        userAccountService.addUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}
