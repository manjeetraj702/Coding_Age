package com.educore.repository;

import com.educore.model.entity.DailyChallenge;
import com.educore.model.entity.DailyChallengeSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyChallengeSubmissionRepository extends MongoRepository<DailyChallengeSubmission, String> {

}
