package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.SubjectCreateRequest;
import com.educore.model.entity.Subject;
import com.educore.repository.SubjectRepository;
import com.educore.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public ApiResponse<Subject> createSubject(SubjectCreateRequest createRequest) {
        Subject subject = new Subject();
        subject.setName(createRequest.getName());
        subject.setTopics(createRequest.getTopics());
        subject.setCreatedAt(LocalDateTime.now());
        return new ApiResponse<>(subjectRepository.save(subject), HttpStatus.CREATED);
    }

    @Override
    public ApiResponse<List<Subject>> fetchSubjects() {
        return new ApiResponse<>(subjectRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ApiResponse<Boolean> removeTopic(String id, int index) {
        Subject subject = Optional.ofNullable(findById(id)).orElseThrow(
                () -> new ApplicationException("Subject not found"));

        if(index >= 0 && index < subject.getTopics().size()){
            List<String> topics = new ArrayList<>(subject.getTopics());
            topics.remove(index);
            subject.setTopics(new LinkedHashSet<>(topics));
            subjectRepository.save(subject);
            return new ApiResponse<>(true, HttpStatus.OK);
        }
        return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Wrong Index value");
    }

    @Override
    public ApiResponse<Boolean> addTopic(String id, String topic) {
        Subject subject = Optional.ofNullable(findById(id)).orElseThrow(
                () -> new ApplicationException("Subject not found"));
        subject.getTopics().add(topic);
        subjectRepository.save(subject);
        return new ApiResponse<>(true, HttpStatus.OK);
    }

    @Override
    public ApiResponse<Boolean> deleteSubject(String id) {
        Subject subject = Optional.ofNullable(findById(id)).orElseThrow(
                () -> new ApplicationException("Subject not found"));
        subjectRepository.delete(subject);
        return new ApiResponse<>(true, HttpStatus.OK);
    }

    @Override
    public Subject findById(String id) {
        String replacedId = id.replaceAll("[{}]", "").trim();
        return subjectRepository.findById(replacedId).orElse(null);
    }
}
