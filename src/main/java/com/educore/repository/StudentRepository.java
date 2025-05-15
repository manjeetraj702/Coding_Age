package com.educore.repository;

import com.educore.model.entity.Student;
import com.educore.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByUser(User user);
}
