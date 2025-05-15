package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.QuizCreateRequest;
import com.educore.model.dto.requests.fetch.QuizFetchRequest;
import com.educore.model.dto.requests.update.QuizUpdateRequest;
import com.educore.model.dto.response.QuizDto;
import com.educore.model.entity.Quiz;
import com.educore.model.enums.Role;
import com.educore.repository.QuizRepository;
import com.educore.service.AdminService;
import com.educore.service.EmployeeService;
import com.educore.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public ApiResponse<QuizDto> createQuiz(QuizCreateRequest createRequest) {
        if(createRequest.getCreatorRole() == Role.ADMIN){
          Optional.ofNullable(adminService.findById(createRequest.getCreatedBy())).orElseThrow(
                    () -> new ApplicationException("Invalid user Id"));
        }else {
            Optional.ofNullable(employeeService.findById(createRequest.getCreatedBy())).orElseThrow(
                    () -> new ApplicationException("Invalid user Id"));
        }
        return new ApiResponse<>(create(createRequest), HttpStatus.CREATED);
    }

    private QuizDto create(QuizCreateRequest createRequest) {
        Quiz quiz = new Quiz();
        quiz.setTitle(createRequest.getTitle());
        quiz.setDescription(createRequest.getDescription());
        quiz.setQuestionIds(createRequest.getQuestionIds());
        quiz.setSubject(createRequest.getSubject());
        quiz.setTopic(createRequest.getTopic());
        quiz.setLevel(createRequest.getLevel());
        quiz.setDifficulty(createRequest.getDifficulty());
        quiz.setCreatedBy(createRequest.getCreatedBy());
        quiz.setCreatedAt(LocalDateTime.now());
        quiz = quizRepository.save(quiz);

        return new QuizDto(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getDescription(),
                quiz.getTotalAttempts(),
                quiz.getQuestionIds(),
                quiz.getSubject(),
                quiz.getTopic(),
                quiz.getLevel(),
                quiz.getDifficulty()
        );
    }

    @Override
    public ApiResponse<Page<QuizDto>> fetchQuiz(QuizFetchRequest fetchRequest) {
                Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(fetchRequest.getPageNo(), fetchRequest.getPageSize(), sort);

        Page<Quiz> quizzes = quizRepository.findAllBySubjectAndTopic(fetchRequest.getSubject(), fetchRequest.getTopic(), pageable);

        Page<QuizDto> quizDTOs = quizzes.map(quiz -> modelMapper.map(quiz, QuizDto.class));

        return  new ApiResponse<>(quizDTOs, HttpStatus.OK);
    }

    @Override
    public ApiResponse<List<QuizDto>> fetchAllQuiz() {
        List<Quiz> quizzes = quizRepository.findAll();
        List<QuizDto> quizDTOs = quizzes.stream()
                .map(quiz -> modelMapper.map(quiz, QuizDto.class))
                .toList();
        return new ApiResponse<>(quizDTOs, HttpStatus.OK);
    }

    @Override
    public ApiResponse<Boolean> attemptQuiz(String studentId, String quizId) {
        Quiz quiz = Optional.ofNullable(findById(quizId)).orElseThrow(
                () -> new ApplicationException("Quiz not found"));
        quiz.getAttemptedBy().add(studentId);
        int attempt = quiz.getTotalAttempts() + 1;
        quiz.setTotalAttempts(attempt);
        quizRepository.save(quiz);
        return new ApiResponse<>(true, HttpStatus.OK);
    }

    @Override
    public ApiResponse<Boolean> updateQuiz(String quizId, QuizUpdateRequest updateRequest) {
        Quiz quiz = Optional.ofNullable(findById(quizId)).orElseThrow(
                () -> new ApplicationException("Quiz not found"));

        quiz.setTitle(updateRequest.getTitle());
        quiz.setDescription(updateRequest.getDescription());
        quiz.setQuestionIds(updateRequest.getQuestionIds());
        quiz.setSubject(updateRequest.getSubject());
        quiz.setTopic(updateRequest.getTopic());
        quiz.setLevel(updateRequest.getLevel());
        quiz.setDifficulty(updateRequest.getDifficulty());
        quizRepository.save(quiz);
        return new ApiResponse<>(true, HttpStatus.OK);
    }

    @Override
    public Quiz findById(String modelId) {
        String id = modelId.replaceAll("[{}]", "").trim();
        return quizRepository.findById(id).orElse(null);
    }
}
