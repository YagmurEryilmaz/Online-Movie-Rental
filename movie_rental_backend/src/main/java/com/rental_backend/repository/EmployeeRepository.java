package com.rental_backend.repository;

import com.rental_backend.entity.Customer;
import com.rental_backend.entity.Employee;
import com.rental_backend.entity.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    List<Employee> findAll();

    @Query("select e.uId from Employee e where e.uId= :userId")
    Employee findByUId(@Param("userId") Long id);

}
