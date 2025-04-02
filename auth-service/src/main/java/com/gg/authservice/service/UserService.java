package com.gg.authservice.service;

import com.gg.authservice.model.User;
import com.gg.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> findByEmail(String email) {

        return userRepository.findByEmail(email);

    }
}
