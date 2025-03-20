package com.javaFullStackProject.e_commerce.services.auth;

import com.javaFullStackProject.e_commerce.dto.SignupRequest;
import com.javaFullStackProject.e_commerce.dto.UserDto;

public interface AuthService {
    public UserDto createUser(SignupRequest signupRequest);

    public Boolean hasUserWithEmail(String email);

    public void createAdminAccount();
}
