package com.mutonya.hmisapplication.auth.models;

import com.mutonya.hmisapplication.auth.models.entity.User;
import com.mutonya.hmisapplication.auth.repo.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final AuthRepo authRepo;
    @Autowired
    private UserService(
            AuthRepo authRepo
    ){
        this.authRepo = authRepo;

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!authRepo.existsByEmail(username)){
            throw  new UsernameNotFoundException("User not found");
        }
        User user = authRepo.findByEmail(username);
        return new UserPrinciple(user);
    }
}
