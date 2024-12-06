package com.example.coderhack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coderhack.model.User;
import com.example.coderhack.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll()
                             .stream()
                             .sorted((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()))
                             .collect(Collectors.toList());
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public User registerUser(User user) {
        user.setScore(0);
        user.setBadges(new HashSet<>());
        return userRepository.save(user);
    }

    public User updateUserScore(String userId, int score) {
        User user = userRepository.findById(userId)
                                   .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (score < 0 || score > 100) throw new IllegalArgumentException("Invalid score");
        user.setScore(score);
        user.updateBadges();
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
