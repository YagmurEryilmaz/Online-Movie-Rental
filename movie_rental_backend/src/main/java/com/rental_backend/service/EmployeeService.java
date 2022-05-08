package com.rental_backend.service;
import com.rental_backend.entity.*;
import com.rental_backend.exception.CustomerNotFoundException;
import com.rental_backend.exception.MovieNotFoundException;
import com.rental_backend.repository.*;
import org.apache.catalina.User;
import org.hibernate.annotations.SQLInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private MovieRepository movieRepository;
    private CustomerRepository customerRepository;
    private UserAccountRepository userAccountRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, MovieRepository movieRepository, CustomerRepository customerRepository, UserAccountRepository userAccountRepository) {
        this.employeeRepository = employeeRepository;
        this.movieRepository = movieRepository;
        this.customerRepository = customerRepository;
        this.userAccountRepository = userAccountRepository;
    }
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public Employee addUser(Employee employee) {
        return employeeRepository.save(employee);
    }
}
