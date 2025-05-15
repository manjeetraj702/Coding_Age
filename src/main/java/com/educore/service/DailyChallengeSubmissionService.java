package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.DailyChallengeCreateRequest;
import com.educore.model.dto.requests.create.DailyChallengeSubmissionCreateRequest;
import com.educore.model.entity.DailyChallenge;

public interface DailyChallengeSubmissionService {

    ApiResponse<Boolean> submitDailyChallenge(DailyChallengeSubmissionCreateRequest submissionCreateRequest);
}
