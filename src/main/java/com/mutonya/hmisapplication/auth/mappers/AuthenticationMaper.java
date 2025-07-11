package com.mutonya.hmisapplication.auth.mappers;

import com.mutonya.hmisapplication.auth.models.dto.RegisterUserRequest;
import com.mutonya.hmisapplication.auth.models.entity.User;
import com.mutonya.hmisapplication.auth.models.responses.LoginResponse;
import com.mutonya.hmisapplication.auth.models.responses.RegisterResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMaper {

    public User toUserEntity(RegisterUserRequest registerUserRequest){

        User user = new User();
        user.setFirstname(registerUserRequest.getFirstname());
        user.setLastname(registerUserRequest.getLastname());
        user.setEmail(registerUserRequest.getEmail());
        return user;


    }
    public  LoginResponse toLoginResponse(User user, String token) {
        return new LoginResponse(
                token,
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getCreatedAt()
        );
    }

    public  RegisterResponse toRegisterResponse(User user) {
        return new RegisterResponse(
                true,
                "User registered successfully",
                user.getId(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getCreatedAt()
        );
    }
}
