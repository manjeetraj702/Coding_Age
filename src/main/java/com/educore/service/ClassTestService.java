package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.TestCreateRequest;
import com.educore.model.entity.ClassTest;

import java.util.List;

public interface ClassTestService {
    ApiResponse<ClassTest> createTest(TestCreateRequest testCreateRequest);

    ApiResponse<Boolean> joinTest(String testId, String studentId);

    ClassTest findById(String testId);

    ApiResponse<List<ClassTest>> getTestsOfBatch(String batchId);
}
