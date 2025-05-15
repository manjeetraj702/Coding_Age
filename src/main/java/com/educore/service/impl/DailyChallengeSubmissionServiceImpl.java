package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.DailyChallengeCreateRequest;
import com.educore.model.dto.requests.create.DailyChallengeSubmissionCreateRequest;
import com.educore.model.entity.DailyChallenge;
import com.educore.model.entity.DailyChallengeSubmission;
import com.educore.model.enums.Role;
import com.educore.repository.DailyChallengeRepository;
import com.educore.repository.DailyChallengeSubmissionRepository;
import com.educore.service.AdminService;
import com.educore.service.DailyChallengeService;
import com.educore.service.DailyChallengeSubmissionService;
import com.educore.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DailyChallengeSubmissionServiceImpl implements DailyChallengeSubmissionService {

    @Autowired
    private DailyChallengeSubmissionRepository dailyChallengeRepository;

    @Autowired
    private DailyChallengeService dailyChallengeService;

    @Override
    public ApiResponse<Boolean> submitDailyChallenge(DailyChallengeSubmissionCreateRequest submissionCreateRequest) {
        DailyChallengeSubmission submission = new DailyChallengeSubmission();
        DailyChallenge dailyChallenge = Optional.ofNullable(dailyChallengeService.findById(submissionCreateRequest.getDailyChallengeId()))
                        .orElseThrow(()-> new ApplicationException("Challenge not found"));

        submission.setDailyChallengeId(submissionCreateRequest.getDailyChallengeId());
        submission.setStudentId(submissionCreateRequest.getStudentId());
        submission.setAnswer(submissionCreateRequest.getAnswer());
        submission.setCorrect(submissionCreateRequest.isCorrect());

        DailyChallengeSubmission savedSubmission = dailyChallengeRepository.save(submission);
        dailyChallengeService.addNewSubmission(savedSubmission.getId(), submissionCreateRequest.getStudentId(), dailyChallenge.getId());
        return new ApiResponse<>(true, HttpStatus.OK);
    }
}
