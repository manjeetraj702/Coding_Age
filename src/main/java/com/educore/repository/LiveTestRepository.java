package com.educore.repository;

import com.educore.model.entity.LiveTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LiveTestRepository extends MongoRepository<LiveTest, String> {
    @Query("{ 'attemptedBy': { $ne: ?0 } }")
    List<LiveTest> findByUserNotAttempted(String userId);
}
