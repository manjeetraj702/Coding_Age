package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.DailyChallengeCreateRequest;
import com.educore.model.entity.DailyChallenge;

import java.util.List;

public interface DailyChallengeService {
    ApiResponse<DailyChallenge> createDailyChallenge(DailyChallengeCreateRequest createRequest);

    ApiResponse<List<DailyChallenge>> fetchDailyChallenges();

    DailyChallenge findById(String id);

    Boolean addNewSubmission(String submissionId, String studentId, String challengeId);
}
