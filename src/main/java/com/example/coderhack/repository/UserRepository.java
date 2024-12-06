package com.example.coderhack.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.coderhack.model.User;

public interface UserRepository extends MongoRepository<User, String> {
}
