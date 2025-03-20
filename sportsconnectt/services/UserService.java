package com.example.sportsconnectt.services;

import com.example.sportsconnectt.models.User;
import com.example.sportsconnectt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email); // Assumes the UserRepository has this method
    }
    

    // Save a new user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(int id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    // Delete a user
    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
