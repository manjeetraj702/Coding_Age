package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.response.StudentListResponse;
import com.educore.model.entity.Student;
import com.educore.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> fetchById(@PathVariable String id){
        ApiResponse<Student> response = studentService.fetchById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/by/ids")
    public ResponseEntity<ApiResponse<List<StudentListResponse>>> fetchByIds(@RequestParam Set<String> ids){
        ApiResponse<List<StudentListResponse>> response = studentService.fetchByIds(ids);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<StudentListResponse>>> fetchAll(){
        ApiResponse<List<StudentListResponse>> response = studentService.fetchAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}