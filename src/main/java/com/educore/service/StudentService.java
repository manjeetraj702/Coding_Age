package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.SignUpRequest;
import com.educore.model.dto.requests.create.StudentSignupRequest;
import com.educore.model.dto.response.StudentListResponse;
import com.educore.model.entity.Student;
import com.educore.model.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface StudentService {

    boolean enrollInBatch(String batchId, String studentId, String courseId);

    Student studentCreate(StudentSignupRequest signUpRequest);

    ApiResponse<Student> fetchById(String id);

    boolean removeFromBatch(String batchId, String studentId);

    ApiResponse<List<StudentListResponse>> fetchByIds(Set<String> ids);

    ApiResponse<List<StudentListResponse>> fetchAll();

    Student create(SignUpRequest signUpRequest);

    Student findByUser(User user);

    Student findById(String studentId);

    List<Student> findAllById(Set<String> ids);
}
