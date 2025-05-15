package com.educore.repository;

import com.educore.model.entity.Question;
import com.educore.model.enums.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

    List<Question> findAllByType(Type typeEnum);
}
