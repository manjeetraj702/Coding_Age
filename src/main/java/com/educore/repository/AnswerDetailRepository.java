package com.educore.repository;

import com.educore.model.entity.AnswerDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDetailRepository extends MongoRepository<AnswerDetail, String> {
}
