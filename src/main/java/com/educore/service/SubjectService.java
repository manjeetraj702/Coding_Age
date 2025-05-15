package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.SubjectCreateRequest;
import com.educore.model.entity.Subject;

import java.util.List;

public interface SubjectService {
    
    ApiResponse<Subject> createSubject(SubjectCreateRequest createRequest);

    ApiResponse<List<Subject>> fetchSubjects();

    ApiResponse<Boolean> removeTopic(String id, int index);

    ApiResponse<Boolean> addTopic(String id, String topic);

    ApiResponse<Boolean> deleteSubject(String id);

    Subject findById(String id);
}
