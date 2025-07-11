package com.mutonya.hmisapplication.auth.models.dto;

import com.mutonya.hmisapplication.auth.models.entity.Role;

import java.util.Set;

public class RegisterUserRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Set<Role> roles;

    public RegisterUserRequest(String firstname, String lastname, String email, String password,Set<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public RegisterUserRequest() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
