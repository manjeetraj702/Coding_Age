package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.AnswerDetailModel;
import com.educore.model.dto.requests.create.TestSubmissionModel;
import com.educore.model.dto.requests.update.SubmissionSubmitRequest;
import com.educore.model.dto.requests.update.UpdateTestSubmission;
import com.educore.model.dto.response.ResultResponse;
import com.educore.model.entity.TestSubmission;

import java.util.List;

public interface TestSubmissionService {
    ApiResponse<TestSubmission> create(TestSubmissionModel testSubmissionModel);

    ApiResponse<Boolean> addAnswers(SubmissionSubmitRequest submissionSubmitRequest);

    ApiResponse<TestSubmission> evaluateSubmission(UpdateTestSubmission updateTestSubmission);

    ApiResponse<List<TestSubmission>> getSubmissionsOfTest(String testId);

    ApiResponse<TestSubmission> getSubmissionsOfTestByStudentId(String testId, String studentId);

    TestSubmission findById(String testSubmissionId);

    ApiResponse<ResultResponse> generateResult(String testId);
}
