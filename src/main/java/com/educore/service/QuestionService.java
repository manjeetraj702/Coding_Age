package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.QuestionCreateRequest;
import com.educore.model.entity.Question;

import java.util.List;
import java.util.Set;

public interface QuestionService {

    ApiResponse<Question> create(QuestionCreateRequest createRequest);

    ApiResponse<List<Question>> fetchAll();


    ApiResponse<List<Question>> fetchAllByType(String type);

    ApiResponse<List<Question>> fetchAllByIds(Set<String> ids);

    Question fetchById(String id);

    List<Question> fetchByIds(Set<String> ids);
}
