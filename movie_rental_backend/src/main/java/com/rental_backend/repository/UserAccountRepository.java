package com.rental_backend.repository;
import com.rental_backend.entity.Movie;
import com.rental_backend.entity.UserAccount;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount,Long> {


    List<UserAccount> findAll();
    UserAccount findUserAccountByEmail(String email);
    boolean existsUserAccountByEmail(String email);
    boolean existsUserAccountByuId(Long uId);


    @Query("select u.uId from UserAccount u where u.uId= :userId")
    UserAccount findByUId(@Param("userId") Long id);

    @Modifying
    @Transactional
    @Query("delete from UserAccount u where u.uId= :uId")
    void deleteUserByUId(@Param("uId") Long uId);





}
