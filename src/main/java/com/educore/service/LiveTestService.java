package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.LiveTestCreateRequest;
import com.educore.model.dto.requests.create.QuizCreateRequest;
import com.educore.model.dto.requests.fetch.QuizFetchRequest;
import com.educore.model.dto.requests.update.QuizUpdateRequest;
import com.educore.model.dto.response.LiveTestDto;
import com.educore.model.dto.response.QuizDto;
import com.educore.model.entity.LiveTest;
import com.educore.model.entity.Quiz;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LiveTestService {

    ApiResponse<LiveTestDto> createLiveTest(LiveTestCreateRequest createRequest);

    //ApiResponse<Page<LiveTestDto>> fetchLiveTest(LiveTestFetchRequest fetchRequest);

    ApiResponse<List<LiveTestDto>> fetchAllLiveTest();

    ApiResponse<List<LiveTestDto>> fetchAllUnAttempted(String studentId);

    ApiResponse<Boolean> attemptLiveTest(String studentId, String liveTestId);
    
    LiveTest findById(String modelId);

    //ApiResponse<Boolean> updateLiveTest(String liveTestId, LiveTestUpdateRequest updateRequest);
}
