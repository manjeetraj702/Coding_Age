package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.EmployeeSignUpRequest;
import com.educore.model.dto.requests.create.UserCreateRequest;
import com.educore.model.entity.Employee;
import com.educore.model.entity.User;
import com.educore.model.enums.Department;
import com.educore.model.enums.Role;
import com.educore.repository.EmployeeRepository;
import com.educore.service.EmployeeService;
import com.educore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;

    @Override
    public Employee create(EmployeeSignUpRequest signUpRequest) {
        User user = userService.create(new UserCreateRequest(
                signUpRequest.getCredential().getName(),
                signUpRequest.getCredential().getEmail(),
                signUpRequest.getCredential().getPhoneNumber(),
                signUpRequest.getCredential().getPassword(),
                Role.EMPLOYEE)
        );
         user = userService.addAddress(user.getId(), signUpRequest.getAddress());

         String jobId = generateJobId(signUpRequest.getDepartment(), signUpRequest.getJoiningDate(), user.getId());

        Employee employee = new Employee();
        employee.setUser(user);
        employee.setJobId(jobId);
        employee.setJoiningDate(signUpRequest.getJoiningDate());
        employee.setDepartment(signUpRequest.getDepartment());
        return employeeRepository.save(employee);
    }

    /// Generate JOB ID method
    private static String generateJobId(Department department, LocalDate joiningDate, String id) {
        String prefix = "C";
        String deptCode = department.name().substring(0, 2).toUpperCase();
        String dateCode = joiningDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));

        // Last 6 characters of MongoDB ObjectId
        String shortId = id.length() >= 6
                ? id.substring(id.length()  - 6)
                : id;

        return prefix + deptCode + dateCode + shortId;
    }

    @Override
    public Employee findByUser(User user) {
        return employeeRepository.findByUser(user);
    }

    @Override
    public Employee findById(String modelId) {
        return  employeeRepository.findById(modelId).orElse(null);
    }

    @Override
    public Employee findByJobId(String jobId) {
        return employeeRepository.findByJobId(jobId);
    }

    @Override
    public ApiResponse<Employee> fetchById(String id) {
        Employee employee = findById(id);
        if(employee == null){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Employee not found");
        }
        return new ApiResponse<>(employee ,HttpStatus.OK);
    }
}
