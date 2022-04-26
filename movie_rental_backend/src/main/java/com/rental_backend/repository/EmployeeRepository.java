package com.rental_backend.repository;

import com.rental_backend.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository <Employee,Long> {
}
