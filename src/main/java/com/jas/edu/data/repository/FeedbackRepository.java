package com.jas.edu.data.repository;

import com.jas.edu.data.entity.UserfeedPK;
import com.jas.edu.data.entity.Usersfeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Usersfeed, UserfeedPK>,
        JpaSpecificationExecutor<Usersfeed> {
    @Query("SELECT u FROM Usersfeed u WHERE u.firstName = :firstName AND u.email = :email ")
    Optional<Usersfeed> findUser(String  firstName , String email);
}
