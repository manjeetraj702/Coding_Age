package com.educore.service;
import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.EmployeeSignUpRequest;
import com.educore.model.dto.requests.create.LoginRequest;
import com.educore.model.dto.requests.create.SignUpRequest;
import com.educore.model.dto.requests.create.StudentSignupRequest;
import com.educore.model.dto.response.AuthResponse;
import com.educore.model.entity.Admin;
import com.educore.model.entity.Employee;
import com.educore.model.entity.Student;
import jakarta.validation.Valid;

import java.util.Map;

public interface AuthService {
    ApiResponse<AuthResponse> login(LoginRequest loginRequest);

    ApiResponse<Student> signUp(@Valid SignUpRequest signUpRequest);

    ApiResponse<Student> studentSignUp(@Valid StudentSignupRequest signUpRequest);

    ApiResponse<Employee> employeeSignUp(@Valid EmployeeSignUpRequest signUpRequest);

    ApiResponse<Admin> adminSignUp(@Valid SignUpRequest signUpRequest);

    ApiResponse<Boolean> updatePassword(String id, String oldPassword, String newPassword);

    ApiResponse<AuthResponse> refreshToken(Map<String, String> request);

}
