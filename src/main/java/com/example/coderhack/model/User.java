package com.example.coderhack.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;



import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String username;
    private int score;
    private List<String> badges;

    public User(String username) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.score = 0;
        this.badges = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getBadges() {
        return badges;
    }

    public void setBadges(List<String> badges) {
        Set<String> uniqueBadges = new HashSet<>(badges);
        this.badges = new ArrayList<>(uniqueBadges);
    }
}



