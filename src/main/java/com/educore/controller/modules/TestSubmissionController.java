package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.AnswerDetailModel;
import com.educore.model.dto.requests.create.TestSubmissionModel;
import com.educore.model.dto.requests.update.SubmissionSubmitRequest;
import com.educore.model.dto.requests.update.UpdateTestSubmission;
import com.educore.model.dto.response.ResultResponse;
import com.educore.model.entity.TestSubmission;
import com.educore.service.TestSubmissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/submission")
public class TestSubmissionController {

    @Autowired
    private TestSubmissionService testSubmissionService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TestSubmission>> createSubmission(@Valid @RequestBody TestSubmissionModel testSubmissionModel){
        ApiResponse<TestSubmission> response = testSubmissionService.create(testSubmissionModel);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/submit")
    public ResponseEntity<ApiResponse<Boolean>> submit(@RequestBody SubmissionSubmitRequest submissionSubmitRequest){
        ApiResponse<Boolean> response = testSubmissionService.addAnswers(submissionSubmitRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/by/testId")
    public ResponseEntity<ApiResponse<List<TestSubmission>>> getSubmissionsOfTest(@RequestParam String testId){
        ApiResponse<List<TestSubmission>> response = testSubmissionService.getSubmissionsOfTest(testId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/by/testId/studentId")
    public ResponseEntity<ApiResponse<TestSubmission>> getSubmissionsOfTestByStudentId(@RequestParam String testId, @RequestParam String studentId){
        ApiResponse<TestSubmission> response = testSubmissionService.getSubmissionsOfTestByStudentId(testId, studentId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PutMapping("/evaluate")
    public ResponseEntity<ApiResponse<TestSubmission>> evaluateSubmission(@RequestBody UpdateTestSubmission updateTestSubmission){
        ApiResponse<TestSubmission> response = testSubmissionService.evaluateSubmission(updateTestSubmission);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PutMapping("/generate-result")
    public ResponseEntity<ApiResponse<ResultResponse>> generateResult(@RequestParam String testId){
        ApiResponse<ResultResponse> response = testSubmissionService.generateResult(testId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
