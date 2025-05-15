package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.DailyChallengeCreateRequest;
import com.educore.model.entity.DailyChallenge;
import com.educore.service.DailyChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/daily_challenge")
@CrossOrigin(origins = "*")
public class DailyChallengeController {

    @Autowired
    private DailyChallengeService dailyChallengeService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<DailyChallenge>> createDailyChallenge(
            @RequestBody DailyChallengeCreateRequest createRequest){
        ApiResponse<DailyChallenge> response = dailyChallengeService.createDailyChallenge(createRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/fetch")
    public ResponseEntity<ApiResponse<List<DailyChallenge>>> fetchDailyChallenges(){
        ApiResponse<List<DailyChallenge>> response = dailyChallengeService.fetchDailyChallenges();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
