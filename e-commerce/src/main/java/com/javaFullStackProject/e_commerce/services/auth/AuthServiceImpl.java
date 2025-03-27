package com.javaFullStackProject.e_commerce.services.auth;

import com.javaFullStackProject.e_commerce.dto.SignupRequest;
import com.javaFullStackProject.e_commerce.dto.UserDto;
import com.javaFullStackProject.e_commerce.entity.User;
import com.javaFullStackProject.e_commerce.enums.UserRole;
import com.javaFullStackProject.e_commerce.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);

        User createdUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        userDto.setEmail(createdUser.getEmail());
        userDto.setName(createdUser.getName());
        userDto.setUserRole(createdUser.getRole());

        return userDto;
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @PostConstruct
    public void createAdminAccount() {
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if (adminAccount == null) {
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(user);
        }
    }
}