package com.mutonya.hmisapplication.auth.controller;


import com.mutonya.hmisapplication.auth.models.dto.LoginRequest;
import com.mutonya.hmisapplication.auth.models.dto.RegisterUserRequest;
import com.mutonya.hmisapplication.auth.models.entity.User;
import com.mutonya.hmisapplication.auth.models.responses.LoginResponse;
import com.mutonya.hmisapplication.auth.models.responses.RegisterResponse;
import com.mutonya.hmisapplication.auth.service.AuthService;
import com.mutonya.hmisapplication.auth.utils.apiresponse.ApiResponse;
import com.mutonya.hmisapplication.auth.utils.apiresponse.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(
            AuthService authService
    ){
        this.authService = authService;
    }


    @PostMapping("/register")

    public  ResponseEntity<ApiResponse<RegisterResponse>> registerUser(
            @RequestBody RegisterUserRequest registerUserRequest
            ){

        RegisterResponse registerResponse = authService.registerUser(registerUserRequest);

        return ResponseBuilder.buildSuccessResponse(registerResponse);

    }
    @PostMapping("/login")

    public ResponseEntity<ApiResponse<LoginResponse>> loginUser(
            @RequestBody LoginRequest loginrequest
    ){

        LoginResponse loginResponse = authService.authenticateUser(loginrequest);

        return ResponseBuilder.buildSuccessResponse(loginResponse);

    }
}
