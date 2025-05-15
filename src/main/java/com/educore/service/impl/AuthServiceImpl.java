package com.educore.service.impl;

import com.educore.authentication.JwtUtil;
import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.EmployeeSignUpRequest;
import com.educore.model.dto.requests.create.LoginRequest;
import com.educore.model.dto.requests.create.SignUpRequest;
import com.educore.model.dto.requests.create.StudentSignupRequest;
import com.educore.model.dto.response.AuthResponse;
import com.educore.model.entity.Admin;
import com.educore.model.entity.Employee;
import com.educore.model.entity.Student;
import com.educore.model.entity.User;
import com.educore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminService adminService;

    @Override
    public ApiResponse<AuthResponse> login(LoginRequest loginRequest) {
        User user = userService.fetchByPhoneNumber(loginRequest.getPhoneNumber());

        if (user == null) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "User not found");
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new ApplicationException("Phone number password not match");
        }

        Student student = studentService.findByUser(user);
        if (student != null) {
            String token = jwtUtil.generateAccessToken(student.getId(), user.getRole());
            userService.updateLastSignIn(user.getId());
            return new ApiResponse<>(new AuthResponse(token), HttpStatus.OK);
        }

        Employee employee = employeeService.findByUser(user);
        if (employee != null) {
            String token = jwtUtil.generateAccessToken(employee.getId(), user.getRole());
            userService.updateLastSignIn(user.getId());
            return new ApiResponse<>(new AuthResponse(token), HttpStatus.OK);
        }

        Admin admin = adminService.findByUser(user);
        if (admin != null) {
            String token = jwtUtil.generateAccessToken(admin.getId(), user.getRole());
            userService.updateLastSignIn(user.getId());
            return new ApiResponse<>(new AuthResponse(token), HttpStatus.OK);
        }

        return new ApiResponse<>(HttpStatus.BAD_REQUEST,"Invalid Credentials");
    }

    @Override
    public ApiResponse<Student> signUp(SignUpRequest signUpRequest) {
        return new ApiResponse<>(studentService.create(signUpRequest), HttpStatus.CREATED);
    }

    @Override
    public ApiResponse<Student> studentSignUp(StudentSignupRequest signUpRequest) {
        return new ApiResponse<>(studentService.studentCreate(signUpRequest), HttpStatus.CREATED);
    }

    @Override
    public ApiResponse<Employee> employeeSignUp(EmployeeSignUpRequest signUpRequest) {
        return new ApiResponse<>(employeeService.create(signUpRequest), HttpStatus.CREATED);
    }

    @Override
    public ApiResponse<Admin> adminSignUp(SignUpRequest signUpRequest) {
        return new ApiResponse<>(adminService.create(signUpRequest), HttpStatus.CREATED);
    }

    @Override
    public ApiResponse<Boolean> updatePassword(String id, String oldPassword, String newPassword) {
        return new ApiResponse<>(userService.updatePassword(id, oldPassword, newPassword), HttpStatus.OK);
    }

    @Override
    public ApiResponse<AuthResponse> refreshToken(Map<String, String> request) {
        String token = request.get("token");

        if (!jwtUtil.validateAccessToken(token)) {
            return new ApiResponse<>(HttpStatus.UNAUTHORIZED,"Invalid Token");
        }

        String newAccessToken = jwtUtil.refreshAccessToken(token);
        return new ApiResponse<>(new AuthResponse(newAccessToken), HttpStatus.OK);
    }
}
