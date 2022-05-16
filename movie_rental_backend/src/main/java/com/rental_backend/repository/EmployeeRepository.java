package com.rental_backend.repository;

import com.rental_backend.entity.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    List<Employee> findAll();

    @Modifying
    @Transactional
    @Query("update Employee e set e.email=:email where e.uId=:uId ")
    void updateEmployeeByUId(@Param("uId") Long uId, @Param("email") String email);

}
