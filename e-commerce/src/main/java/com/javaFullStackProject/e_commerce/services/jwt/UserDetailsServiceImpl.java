package com.javaFullStackProject.e_commerce.services.jwt;

import com.javaFullStackProject.e_commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.javaFullStackProject.e_commerce.entity.User;



import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findFirstByEmail(username);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User Name Not Found" , null);
        }

        return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(),  optionalUser.get().getPassword() , new ArrayList<>());
    }
}
