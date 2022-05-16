package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, MovieRepository movieRepository, CustomerRepository customerRepository, UserAccountRepository userAccountRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee addUser(Employee employee) {
        return employeeRepository.save(employee);
    }
}
