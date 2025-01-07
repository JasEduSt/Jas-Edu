package com.jas.edu.data.repository;

import com.jas.edu.data.entity.UserDetails;
import com.jas.edu.data.entity.UserDetailsPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDetaillsRepository extends JpaRepository<UserDetails, UserDetailsPk>,
        JpaSpecificationExecutor<UserDetails> {
    @Query("SELECT u FROM UserDetails u WHERE u.firstName = :firstName AND u.email = :email ")
    Optional<UserDetails> findUser(String  firstName , String email);
    @Query("SELECT u FROM UserDetails u WHERE u.userName = :userName AND u.password = :password ")
    UserDetails findByUsernameAndPassword(String userName, String password);
    @Query("SELECT u.password FROM UserDetails u WHERE u.userName = :username ")
    String findPassword(String username);
}
