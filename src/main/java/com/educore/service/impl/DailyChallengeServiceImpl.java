package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.DailyChallengeCreateRequest;
import com.educore.model.entity.DailyChallenge;
import com.educore.model.entity.Student;
import com.educore.model.enums.Role;
import com.educore.repository.DailyChallengeRepository;
import com.educore.service.AdminService;
import com.educore.service.DailyChallengeService;
import com.educore.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DailyChallengeServiceImpl implements DailyChallengeService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DailyChallengeRepository dailyChallengeRepository;

    @Override
    public ApiResponse<DailyChallenge> createDailyChallenge(DailyChallengeCreateRequest createRequest) {
        if(createRequest.getCreatorRole() == Role.ADMIN){
            Optional.ofNullable(adminService.findById(createRequest.getCreatedBy())).orElseThrow(
                    () -> new ApplicationException("User not found"));
        }else {
            Optional.ofNullable(employeeService.findById(createRequest.getCreatedBy())).orElseThrow(
                    () -> new ApplicationException("User not found"));
        }
        DailyChallenge dailyChallenge = new DailyChallenge();
        dailyChallenge.setQuestion(createRequest.getQuestion());
        dailyChallenge.setDescription(createRequest.getDescription());
        dailyChallenge.setCreatedBy(createRequest.getCreatedBy());
        return new ApiResponse<>(dailyChallengeRepository.save(dailyChallenge), HttpStatus.CREATED);
    }

    @Override
    public ApiResponse<List<DailyChallenge>> fetchDailyChallenges() {
        LocalDateTime cutoffTime = LocalDateTime.now().minusHours(24);
        return new ApiResponse<>(dailyChallengeRepository.findByCreatedAtAfter(cutoffTime), HttpStatus.OK);
    }

    @Override
    public DailyChallenge findById(String id) {
        String replacedId = id.replaceAll("[{}]", "").trim();
        return dailyChallengeRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean addNewSubmission(String submissionId, String studentId, String challengeId) {
        DailyChallenge dailyChallenge = findById(challengeId);
        dailyChallenge.getStudentsId().add(studentId);
        dailyChallenge.getSubmissionsId().add(submissionId);
        int newNumber = dailyChallenge.getNoOfSubmissions() + 1;
        dailyChallenge.setNoOfSubmissions(newNumber);
        dailyChallengeRepository.save(dailyChallenge);
        return true;
    }
}

