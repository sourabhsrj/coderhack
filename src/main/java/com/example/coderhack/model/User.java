package com.example.coderhack.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.HashSet;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String userId; // Unique identifier
    private String username;
    private int score = 0; // Default to 0
    private Set<String> badges = new HashSet<>(); // Default to empty

    public void updateBadges() {
        badges.clear();
        if (score >= 1 && score < 30) badges.add("Code Ninja");
        if (score >= 30 && score < 60) badges.add("Code Champ");
        if (score >= 60 && score <= 100) badges.add("Code Master");
    }
}

