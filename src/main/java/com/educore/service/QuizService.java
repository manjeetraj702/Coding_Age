package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.QuizCreateRequest;
import com.educore.model.dto.requests.fetch.QuizFetchRequest;
import com.educore.model.dto.requests.update.QuizUpdateRequest;
import com.educore.model.dto.response.QuizDto;
import com.educore.model.entity.Quiz;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuizService {

    ApiResponse<QuizDto> createQuiz(QuizCreateRequest createRequest);

    ApiResponse<Page<QuizDto>> fetchQuiz(QuizFetchRequest fetchRequest);

    ApiResponse<List<QuizDto>> fetchAllQuiz();

    ApiResponse<Boolean> attemptQuiz(String studentId, String quizId);
    
    Quiz findById(String modelId);

    ApiResponse<Boolean> updateQuiz(String quizId, QuizUpdateRequest updateRequest);
}
