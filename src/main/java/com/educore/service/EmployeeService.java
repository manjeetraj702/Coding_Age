package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.EmployeeSignUpRequest;
import com.educore.model.entity.Employee;
import com.educore.model.entity.User;

import java.time.LocalDateTime;

public interface EmployeeService {
    Employee create(EmployeeSignUpRequest signUpRequest);

    Employee findByUser(User user);

    Employee findById(String modelId);

    Employee findByJobId(String jobId);

    ApiResponse<Employee> fetchById(String id);
}
