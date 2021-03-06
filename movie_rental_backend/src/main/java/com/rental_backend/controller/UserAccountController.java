package com.rental_backend.controller;

import com.rental_backend.dto.UserResponse;
import com.rental_backend.entity.UserAccount;
import com.rental_backend.service.EmployeeService;
import com.rental_backend.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/userAccount")
@RequiredArgsConstructor

public class UserAccountController {
    private UserAccountService userAccountService;
    private EmployeeService employeeService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService,EmployeeService employeeService) {
        this.userAccountService = userAccountService;
        this.employeeService=employeeService;
    }
    @DeleteMapping("/deleteUserByUId")
    public ResponseEntity<?> deleteUserByUId(@PathVariable UserResponse user) {
        userAccountService.deleteUserByUId(user.getUId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/deleteAndReturnUser")
    public ResponseEntity<?> deleteAndReturnUser(@PathVariable UserAccount user) {
        return ResponseEntity.ok(userAccountService.deleteAndReturnUser(user)) ;
    }
    @PatchMapping("/updateUserInfoByUId/{uId}/{email}")
    public ResponseEntity<?> updateUserInfoByUId(@PathVariable Long uId, @PathVariable String email) {
        employeeService.updateEmployeeByUId(uId, email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
