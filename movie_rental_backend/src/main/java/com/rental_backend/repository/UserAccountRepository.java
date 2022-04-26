package com.rental_backend.repository;
import com.rental_backend.entity.Movie;
import com.rental_backend.entity.UserAccount;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount,Long> {
    List<UserAccount> findAll();

    @Query("delete from UserAccount where uId= :userId")
    List<UserAccount> deleteAcc(@Param("searchTerm") String userId);

}
