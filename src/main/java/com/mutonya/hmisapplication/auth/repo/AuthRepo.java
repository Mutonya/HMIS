package com.mutonya.hmisapplication.auth.repo;

import com.mutonya.hmisapplication.auth.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);


    //check if the email is already taken
    boolean existsByEmail(String email);
}
