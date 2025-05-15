package com.educore.repository;
import com.educore.model.entity.DailyChallenge;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DailyChallengeRepository extends MongoRepository<DailyChallenge, String> {

    List<DailyChallenge> findByCreatedAtAfter(LocalDateTime cutoffTime);
}
