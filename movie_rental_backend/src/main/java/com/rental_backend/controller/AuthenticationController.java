package com.rental_backend.controller;
import com.rental_backend.dto.LoginRequest;
import com.rental_backend.dto.MessageResponse;
import com.rental_backend.dto.SignupRequest;
import com.rental_backend.dto.UserResponse;
import com.rental_backend.entity.Employee;
import com.rental_backend.entity.UserAccount;
import com.rental_backend.service.EmployeeService;
import com.rental_backend.service.UserAccountService;
import com.rental_backend.entity.Customer;
import com.rental_backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserAccountService userAccountService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    @PostMapping("/signin")
    public UserResponse signIn ( @RequestBody LoginRequest loginRequest)  {

        if (userAccountService.existsByEmail(loginRequest.getEmail()))
            if (loginRequest.getEmail() != null)
            {
                String providedPassword = loginRequest.getPassword();
                String requiredPassword = userAccountService.findByEmail(loginRequest.getEmail()).getPassword();
                if (providedPassword.equals(requiredPassword)) {
                    UserAccount user = userAccountService.findByEmail(loginRequest.getEmail());
                    if(user.getRole().equals("customer") ){
                        float balance  = customerService.findByEmail(loginRequest.getEmail()).getBalance();
                        return UserResponse.builder()
                                .success(true)
                                .uId(user.getUId())
                                .birthday(user.getBirthday())
                                .email(user.getEmail())
                                .role(user.getRole())
                                .name(user.getName())
                                .balance(balance)
                                .build();
                    }
                    return UserResponse.builder()
                            .success(true)
                            .uId(user.getUId())
                            .birthday(user.getBirthday())
                            .email(user.getEmail())
                            .role(user.getRole())
                            .name(user.getName())
                            .balance(0)
                            .build();
                }
            }
        return UserResponse.builder().success(false).build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
        if (userAccountService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.ok(new MessageResponse("Error: Email is already in use!"));
        }

        if(signUpRequest.getRole().equals("customer"))
        {
            Customer customer = Customer
                    .builder()
                    .email(signUpRequest.getEmail())
                    .password(signUpRequest.getPassword())
                    .role(signUpRequest.getRole())
                    .name(signUpRequest.getName())
                    .birthday(signUpRequest.getBirthday())
                    .build();
            customerService.addUser(customer);
        }

        else if (signUpRequest.getRole().equals("admin"))
        {
            Employee employee = Employee
                    .builder()
                    .email(signUpRequest.getEmail())
                    .password(signUpRequest.getPassword())
                    .role(signUpRequest.getRole())
                    .name(signUpRequest.getName())
                    .birthday(signUpRequest.getBirthday())
                    .build();
            employeeService.addUser(employee);
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}