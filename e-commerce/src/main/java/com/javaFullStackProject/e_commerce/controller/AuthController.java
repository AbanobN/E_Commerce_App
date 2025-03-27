package com.javaFullStackProject.e_commerce.controller;

import com.javaFullStackProject.e_commerce.dto.AuthenticationRequest;
import com.javaFullStackProject.e_commerce.dto.AuthenticationResponse;
import com.javaFullStackProject.e_commerce.dto.SignupRequest;
import com.javaFullStackProject.e_commerce.dto.UserDto;
import com.javaFullStackProject.e_commerce.entity.User;
import com.javaFullStackProject.e_commerce.repository.UserRepository;
import com.javaFullStackProject.e_commerce.services.auth.AuthService;
import com.javaFullStackProject.e_commerce.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository; // Added to fetch user details

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\": \"Invalid credentials\", \"message\": \"Incorrect email or password\"}");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // Fetch user entity to build UserDto
        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found after authentication"));
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setUserRole(user.getRole());

        // Return both JWT and UserDto in the response
        return ResponseEntity.ok(new AuthenticationResponse(jwt, userDto));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("{\"error\": \"Email already exists\", \"message\": \"User with this email already registered\"}");
        }

        UserDto userDto = authService.createUser(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}