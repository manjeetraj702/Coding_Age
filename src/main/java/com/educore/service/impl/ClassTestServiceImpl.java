package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.TestCreateRequest;
import com.educore.model.entity.ClassTest;
import com.educore.model.entity.Question;
import com.educore.repository.ClassTestRepository;
import com.educore.service.QuestionService;
import com.educore.service.StudentService;
import com.educore.service.ClassTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassTestServiceImpl implements ClassTestService {

    @Autowired
    private ClassTestRepository classTestRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private StudentService studentService;

    @Override
    public ApiResponse<ClassTest> createTest(TestCreateRequest testCreateRequest) {
        Duration duration = Duration.between(testCreateRequest.getStartTime(), testCreateRequest.getEndTime());
        ClassTest classTest = new ClassTest(
                testCreateRequest.getTopic(),
                testCreateRequest.getDescription(), duration,
                testCreateRequest.getStartTime(), testCreateRequest.getEndTime(),
                LocalDate.now(), testCreateRequest.getExaminerId()
        );

        classTest.setCategory(testCreateRequest.getCategory());
        classTest.setJoiningId(testCreateRequest.getJoiningId());
        classTest.setExaminerName(testCreateRequest.getExaminerName());
        classTest.setBatchId(testCreateRequest.getBatchId());
        List<Question> questions = testCreateRequest.getQuestionsId().stream()
                .map(id -> Optional.ofNullable(questionService.fetchById(id))
                        .orElseThrow(() -> new ApplicationException("Question not found for ID: " + id)))
                .collect(Collectors.toList());
        classTest.setQuestions(questions);
        return new ApiResponse<>(classTestRepository.save(classTest), HttpStatus.CREATED);
    }

    @Override
    public ApiResponse<List<ClassTest>> getTestsOfBatch(String batchId) {
        Sort sort = Sort.by(Sort.Order.desc("date"), Sort.Order.desc("startingTime"));
        return new ApiResponse<>(classTestRepository.findAllByBatchId(batchId, sort), HttpStatus.OK);
    }

    @Override
    public ApiResponse<Boolean> joinTest(String testId, String studentId) {
        ClassTest classTest = findById(testId);
        if(classTest == null){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "ClassTest id invalid");
        }
        if (classTest.isComplete()) {
            return new ApiResponse<>(HttpStatus.GONE, "Test has been completed");
        }
        if(studentService.findById(studentId) == null){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Student not found");
        }
        if (classTest.getStudentsId().contains(studentId)) {
            return new ApiResponse<>(HttpStatus.CONFLICT, "Student has been already registered");
        }
        classTest.getStudentsId().add(studentId);
        classTestRepository.save(classTest);
        return new ApiResponse<>(true, HttpStatus.OK);
    }

    @Override
    public ClassTest findById(String testId) {
        return classTestRepository.findById(testId).orElse(null);
    }
}