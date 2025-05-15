package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.LiveTestCreateRequest;
import com.educore.model.dto.response.LiveTestDto;
import com.educore.model.entity.LiveTest;
import com.educore.model.enums.Role;
import com.educore.repository.LiveTestRepository;
import com.educore.service.AdminService;
import com.educore.service.EmployeeService;
import com.educore.service.LiveTestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiveTestServiceImpl implements LiveTestService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LiveTestRepository liveTestRepository;

    @Override
    public ApiResponse<LiveTestDto> createLiveTest(LiveTestCreateRequest createRequest) {
        if(createRequest.getCreatorRole() == Role.ADMIN){
            Optional.ofNullable(adminService.findById(createRequest.getCreatedBy())).orElseThrow(
                    () -> new ApplicationException("Invalid user Id"));
        }else {
            Optional.ofNullable(employeeService.findById(createRequest.getCreatedBy())).orElseThrow(
                    () -> new ApplicationException("Invalid user Id"));
        }
        return new ApiResponse<>(create(createRequest), HttpStatus.CREATED);
    }

    private LiveTestDto create(LiveTestCreateRequest createRequest) {
        LiveTest liveTest = new LiveTest();
        liveTest.setTitle(createRequest.getTitle());
        liveTest.setDescription(createRequest.getDescription());
        liveTest.setQuestionIds(createRequest.getQuestionIds());
        liveTest.setSubject(createRequest.getSubject());
        liveTest.setTopic(createRequest.getTopic());
        liveTest.setLevel(createRequest.getLevel());
        liveTest.setDifficulty(createRequest.getDifficulty());
        liveTest.setCreatedBy(createRequest.getCreatedBy());
        liveTest.setDuration(createRequest.getDuration());
        liveTest = liveTestRepository.save(liveTest);
        return new LiveTestDto(
                liveTest.getId(),
                liveTest.getTitle(),
                liveTest.getDescription(),
                liveTest.getParticipants(),
                liveTest.getQuestionIds(),
                liveTest.getSubject(),
                liveTest.getTopic(),
                liveTest.getLevel(),
                liveTest.getDifficulty(),
                liveTest.getDuration()
        );
    }

    @Override
    public ApiResponse<List<LiveTestDto>> fetchAllLiveTest() {
        List<LiveTest> liveTests = liveTestRepository.findAll();
        List<LiveTestDto> liveTestDTOs = liveTests.stream()
                .map(test -> modelMapper.map(test, LiveTestDto.class))
                .toList();
        return new ApiResponse<>(liveTestDTOs, HttpStatus.OK);
    }

    @Override
    public ApiResponse<List<LiveTestDto>> fetchAllUnAttempted(String studentId){
        List<LiveTest> liveTests = liveTestRepository.findByUserNotAttempted(studentId);
        List<LiveTestDto> liveTestDTOs = liveTests.stream()
                .map(test -> modelMapper.map(test, LiveTestDto.class))
                .toList();
        return new ApiResponse<>(liveTestDTOs, HttpStatus.OK);
    }

    @Override
    public ApiResponse<Boolean> attemptLiveTest(String studentId, String liveTestId) {
        LiveTest test = Optional.ofNullable(findById(liveTestId)).orElseThrow(
                () -> new ApplicationException("Test not found"));
        test.getAttemptedBy().add(studentId);
        int participants = test.getParticipants() + 1;
        test.setParticipants(participants);
        liveTestRepository.save(test);
        return new ApiResponse<>(true, HttpStatus.OK);
    }

    @Override
    public LiveTest findById(String modelId) {
        String id = modelId.replaceAll("[{}]", "").trim();
        return liveTestRepository.findById(id).orElse(null);
    }
}
