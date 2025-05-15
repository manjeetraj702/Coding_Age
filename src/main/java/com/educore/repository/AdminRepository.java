package com.educore.repository;

import com.educore.model.entity.Admin;
import com.educore.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByUser(User user);
}
