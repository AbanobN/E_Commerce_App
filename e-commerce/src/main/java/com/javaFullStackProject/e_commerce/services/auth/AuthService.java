package com.javaFullStackProject.e_commerce.services.auth;

import com.javaFullStackProject.e_commerce.dto.SignupRequest;
import com.javaFullStackProject.e_commerce.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
    boolean hasUserWithEmail(String email);
    void createAdminAccount();
}