package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.BatchCreateRequest;
import com.educore.model.entity.Batch;

import java.util.List;
import java.util.Set;

public interface BatchService {

    ApiResponse<Batch> createBatch(BatchCreateRequest batchCreateRequest);

    ApiResponse<Boolean> enrollStudent(String batchId, String studentId);

    Batch findById(String id);

    ApiResponse<List<Batch>> fetchAllBatches();

    ApiResponse<Batch> fetchBatch(String batchId);

    void markSession(String batchId, String id);

    ApiResponse<List<Batch>> fetchBatchesByIds(Set<String> batchIds);

    ApiResponse<Boolean> removeStudent(String batchId, String studentId);
}
