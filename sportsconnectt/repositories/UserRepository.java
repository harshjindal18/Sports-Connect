package com.example.sportsconnectt.repositories;


import com.example.sportsconnectt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}

    
