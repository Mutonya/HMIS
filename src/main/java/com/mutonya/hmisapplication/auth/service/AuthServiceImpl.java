package com.mutonya.hmisapplication.auth.service;

import com.mutonya.hmisapplication.auth.models.UserPrinciple;
import com.mutonya.hmisapplication.auth.models.dto.LoginRequest;
import com.mutonya.hmisapplication.auth.models.dto.RegisterUserRequest;
import com.mutonya.hmisapplication.auth.models.entity.User;
import com.mutonya.hmisapplication.auth.models.responses.LoginResponse;
import com.mutonya.hmisapplication.auth.models.responses.RegisterResponse;
import com.mutonya.hmisapplication.auth.repo.AuthRepo;
import com.mutonya.hmisapplication.auth.mappers.AuthenticationMaper;
import com.mutonya.hmisapplication.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService{
    private  final AuthRepo authrepo;
    private final AuthenticationMaper maper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;



    /**
     * Dependency Injection through a constructor
     * @param authrepo
     */
    @Autowired
    public AuthServiceImpl(
            AuthRepo authrepo,
            AuthenticationMaper mapper,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService

    ) {
        this.authrepo = authrepo;
        this.maper = mapper;
        this.passwordEncoder =passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;

    }
    @Override
    public RegisterResponse registerUser(RegisterUserRequest userRegistrationRequest) {

        User user = createFromRequest(userRegistrationRequest);

        return maper.toRegisterResponse(user);
    }

    @Override
    public LoginResponse authenticateUser(LoginRequest authenticationRequest) {
        //check if the user has an account

        if (!authrepo.existsByEmail(authenticationRequest.getEmail())){
            throw  new RuntimeException("User not registered");
        }

        //check against the encoded value in db this is a manual check the one below is done by SpringSecurity

//        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid credentials");
//        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials");
        }
        User user = authrepo.findByEmail(authenticationRequest.getEmail());

        UserDetails userDetails = new UserPrinciple(user); //  Convert to UserDetails
        String token = jwtService.generateToken(userDetails);




        return maper.toLoginResponse(user,token);
    }

    private User createFromRequest(RegisterUserRequest registerUserRequest){
        //check if the email exists
        if (authrepo.existsByEmail(registerUserRequest.getEmail())){
            throw new RuntimeException("Email already taken");
        }
        //map the request to userEntity
        User user = maper.toUserEntity(registerUserRequest);
        //we shall set the password here if we are encrypting
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        //add the timestamp here
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        //we shall set the roles here so we can edit in future and ensure the roles exist in the db

        user.setRoles(registerUserRequest.getRoles());


        //write to the db and return the value of user

        return authrepo.save(user);



    }
}
