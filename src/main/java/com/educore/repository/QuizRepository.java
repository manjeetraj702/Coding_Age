package com.educore.repository;

import com.educore.model.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends MongoRepository <Quiz, String> {

    Page<Quiz> findAllBySubjectAndTopic(String subject, String topic, Pageable pageable);
}
