package com.pm.authservice.service;

import com.pm.authservice.modle.User;
import com.pm.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
