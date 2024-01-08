package com.abhijeet.noteapp.Repository;

import com.abhijeet.noteapp.Entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Users, String> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findById(String id);
}
