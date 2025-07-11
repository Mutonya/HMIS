package com.mutonya.hmisapplication.auth.models.responses;


import java.time.LocalDateTime;

public class RegisterResponse {

    private boolean success;
    private String message;
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private LocalDateTime createdAt;

    public RegisterResponse(boolean success, String message, Long id, String email, String firstname, String lastname, LocalDateTime createdAt) {
        this.success = success;
        this.message = message;
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.createdAt = createdAt;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
