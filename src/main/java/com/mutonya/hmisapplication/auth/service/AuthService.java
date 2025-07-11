package com.mutonya.hmisapplication.auth.service;

import com.mutonya.hmisapplication.auth.models.dto.LoginRequest;
import com.mutonya.hmisapplication.auth.models.dto.RegisterUserRequest;
import com.mutonya.hmisapplication.auth.models.entity.User;
import com.mutonya.hmisapplication.auth.models.responses.LoginResponse;
import com.mutonya.hmisapplication.auth.models.responses.RegisterResponse;

public interface AuthService {


    RegisterResponse registerUser(RegisterUserRequest userRegistrationRequest);
    LoginResponse authenticateUser(LoginRequest authenticationRequest);
}
