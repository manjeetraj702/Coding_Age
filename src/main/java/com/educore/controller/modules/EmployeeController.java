package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.entity.Employee;
import com.educore.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> fetchById(@PathVariable String id){
        ApiResponse<Employee> response = employeeService.fetchById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
