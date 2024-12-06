package com.example.coderhack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coderhack.model.User;
import com.example.coderhack.repository.UserRepository;

import java.util.List;
import java.util.Optional;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User updateUserScore(String userId, User user) {
        if (user.getScore() < 0 || user.getScore() > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }

        User updateUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        updateUser.setScore(user.getScore());
        HashSet<String> hs=new HashSet<>(updateUser.getBadges());

        hs.addAll(assignBadges(user.getScore()));
        // hs.a
        updateUser.setBadges(new ArrayList<>(hs));
        return userRepository.save(updateUser);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.sort(Comparator.comparingInt(User::getScore).reversed());
        return users;
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    private List<String> assignBadges(int score) {
        List<String> badges = new ArrayList<>();
        if (score >= 1 && score < 30) badges.add("Code Ninja");
        if (score >= 30 && score < 60) 
        {
            badges.add("Code Ninja");
            badges.add("Code Champ");
        }
        if (score >= 60 && score <= 100) 
        {
            
            badges.add("Code Ninja");
            badges.add("Code Champ");
            badges.add("Code Master");
        }
        return badges;
    }
}

