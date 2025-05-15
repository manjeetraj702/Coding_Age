package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.QuestionCreateRequest;
import com.educore.model.entity.Question;
import com.educore.model.enums.Type;
import com.educore.repository.QuestionRepository;
import com.educore.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public ApiResponse<Question> create(QuestionCreateRequest createRequest) {
        Question question = new Question();
        question.setType(createRequest.getType());
        question.setDifficulty(createRequest.getDifficulty());
        question.setSubject(createRequest.getSubject());
        question.setTopic(createRequest.getTopic());
        question.setContent(createRequest.getContent());
        if(createRequest.getOptions() != null){
            question.setOptions(createRequest.getOptions());
        }
        question.setDescription(createRequest.getDescription());
        question.setCreatedBy(createRequest.getCreatedBy());
        return new ApiResponse<>(questionRepository.save(question), HttpStatus.CREATED);
    }

    @Override
    public ApiResponse<List<Question>> fetchAll() {
        return new ApiResponse<>(questionRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ApiResponse<List<Question>> fetchAllByType(String type) {
        Type typeEnum = Type.valueOf(type);
        return new ApiResponse<>(questionRepository.findAllByType(typeEnum), HttpStatus.OK);
    }

    @Override
    public ApiResponse<List<Question>> fetchAllByIds(Set<String> ids) {
        return new ApiResponse<>(fetchByIds(ids), HttpStatus.OK);
    }

    @Override
    public Question fetchById(String id) {
        return  questionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Question> fetchByIds(Set<String> ids) {
        final Set<String> replacedIds = ids.stream()
                .map(id -> id.replaceAll("[{}]", "").trim())
                .collect(Collectors.toSet());
        return questionRepository.findAllById(replacedIds);
    }
}
