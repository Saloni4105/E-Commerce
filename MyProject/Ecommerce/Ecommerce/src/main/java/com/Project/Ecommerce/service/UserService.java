package com.Project.Ecommerce.service;

import com.Project.Ecommerce.model.User;
import com.Project.Ecommerce.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        try {
            User newUser = userRepository.save(user);
            logger.info("User successfully registered with email: {}", user.getEmail());
            return newUser;
        } catch (DataAccessException e) {
            logger.error("Database error while registering user: {}", e.getMessage());
            throw new RuntimeException("Failed to register user", e);
        }
    }

    public User loginUser(String email, String password) {
        // TODO: CRITICAL SECURITY ISSUE - Implement password hashing with BCrypt
        // This is a temporary implementation - passwords should be hashed!
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            logger.info("User login successful for email: {}", email);
            return user;
        }
        logger.warn("Login attempt failed for email: {}", email);
        return null; // invalid credentials
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

