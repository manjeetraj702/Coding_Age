package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.CourseCreateRequest;
import com.educore.model.entity.Course;
import com.educore.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody CourseCreateRequest courseCreateRequest){
        ApiResponse<Course> response = courseService.createCourse(courseCreateRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<List<Course>>> fetchAllCourse(){
        ApiResponse<List<Course>> response = courseService.fetchAllCourse();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
