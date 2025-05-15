package com.educore.repository;

import com.educore.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByPhoneNumber(String phoneNumber);

    User findByEmail(String newEmail);
}
