package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.SignUpRequest;
import com.educore.model.dto.requests.create.StudentSignupRequest;
import com.educore.model.dto.requests.create.UserCreateRequest;
import com.educore.model.dto.response.StudentListResponse;
import com.educore.model.entity.Student;
import com.educore.model.entity.User;
import com.educore.model.enums.Role;
import com.educore.repository.StudentRepository;
import com.educore.service.StudentService;
import com.educore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserService userService;

    @Override
    public Student create(SignUpRequest signUpRequest) {
        User user = userService.create(new UserCreateRequest(
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getPhoneNumber(),
                signUpRequest.getPassword(),
                Role.STUDENT)
        );
        Student student = new Student();
        student.setUser(user);
        student.setEnrolled(false);
        return studentRepository.save(student);
    }

    @Override
    public Student studentCreate(StudentSignupRequest signUpRequest) {
        User user;
        Student student;
        if(signUpRequest.getSignUpRequest() != null){
            user = userService.create(new UserCreateRequest(
                    signUpRequest.getSignUpRequest().getName(),
                    signUpRequest.getSignUpRequest().getEmail(),
                    signUpRequest.getSignUpRequest().getPhoneNumber(),
                    signUpRequest.getSignUpRequest().getPassword(),
                    Role.STUDENT
                    ));
            user = userService.addAddress(user.getId(), signUpRequest.getAddress());
            student = new Student();
            student.setUser(user);
        }else{
            student = Optional.ofNullable(findByUser(userService.findById(signUpRequest.getUserId()))).orElseThrow(
                    () -> new ApplicationException("Student not found"));
            user = userService.addAddress(signUpRequest.getUserId(), signUpRequest.getAddress());
            student.setUser(user);
        }
        student.setEnrolled(true);
        student.setParentMobNo(signUpRequest.getParentMobNo());
        student.setEnrollmentDate(signUpRequest.getEnrollmentDate());
        return studentRepository.save(student);
    }

    @Override
    public ApiResponse<Student> fetchById(String id) {
        Student student = findById(id);
        if(student == null){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Student not found");
        }
        return new ApiResponse<>(student ,HttpStatus.OK);
    }

    @Override
    public boolean enrollInBatch(String batchId, String studentId, String courseId) {
        Student student = Optional.ofNullable(findById(studentId)).orElseThrow(
                () -> new ApplicationException("Student not found"));
        student.getBatchId().add(batchId);
        student.getCourseId().add(courseId);
        student.setEnrolled(true);
        if(student.getEnrollmentDate() == null){
            student.setEnrollmentDate(LocalDateTime.now());
        }
        studentRepository.save(student);
        return true;
    }

    @Override
    public boolean removeFromBatch(String batchId, String studentId) {
        Student student = Optional.ofNullable(findById(studentId)).orElseThrow(
                () -> new ApplicationException("Student not found"));
        student.getBatchId().remove(batchId);
        studentRepository.save(student);
        return true;
    }

    @Override
    public Student findByUser(User user) {
        return studentRepository.findByUser(user);
    }

    @Override
    public ApiResponse<List<StudentListResponse>> fetchByIds(Set<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Batch Doesn't have students");
        }

        List<StudentListResponse> studentResponses = findAllById(ids).stream().map(
                (student) -> new StudentListResponse(
                        student.getId(), student.getUser().getName()
                )).collect(Collectors.toList());
        return new ApiResponse<>(studentResponses, HttpStatus.OK);
    }

    @Override
    public ApiResponse<List<StudentListResponse>> fetchAll() {
        List<Student> studentsList = studentRepository.findAll();
        if(studentsList.isEmpty()){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Couldn't have students");
        }
        List<StudentListResponse> students = new ArrayList<>();
        for(Student student : studentsList){
            students.add(new StudentListResponse(
                    student.getId(), student.getUser().getName(), student.getUsername()
            ));
        }
        return new ApiResponse<>(students, HttpStatus.OK);
    }

    @Override
    public Student findById(String studentId) {
        String id = studentId.replaceAll("[{}]", "").trim();
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> findAllById(Set<String> ids) {
        final Set<String> replacedIds = ids.stream()
                .map(id -> id.replaceAll("[{}]", "").trim())
                .collect(Collectors.toSet());
        return studentRepository.findAllById(replacedIds);
    }
}
