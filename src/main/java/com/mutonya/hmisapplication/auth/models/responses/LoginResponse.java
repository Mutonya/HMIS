package com.mutonya.hmisapplication.auth.models.responses;


import java.time.LocalDateTime;

public class LoginResponse {

    private String token;
    private String email;
    private String firstname;
    private String lastname;
    private LocalDateTime createdAt;



    public LoginResponse(String token, String email, String firstname, String lastname,LocalDateTime createdAt) {
        this.token = token;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.createdAt = createdAt;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
