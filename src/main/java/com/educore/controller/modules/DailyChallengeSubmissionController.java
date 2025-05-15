package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.DailyChallengeCreateRequest;
import com.educore.model.dto.requests.create.DailyChallengeSubmissionCreateRequest;
import com.educore.model.entity.DailyChallenge;
import com.educore.service.DailyChallengeService;
import com.educore.service.DailyChallengeSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/daily_challenge_submission")
@CrossOrigin(origins = "*")
public class DailyChallengeSubmissionController {

    @Autowired
    private DailyChallengeSubmissionService dailyChallengeSubmissionService;

    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<Boolean>> submitDailyChallenge(
            @RequestBody DailyChallengeSubmissionCreateRequest submissionCreateRequest){
        ApiResponse<Boolean> response = dailyChallengeSubmissionService.submitDailyChallenge(submissionCreateRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
