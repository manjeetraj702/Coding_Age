package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.CourseCreateRequest;
import com.educore.model.entity.Admin;
import com.educore.model.entity.Course;
import com.educore.repository.CourseRepository;
import com.educore.service.AdminService;
import com.educore.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AdminService adminService;

    @Override
    public ApiResponse<Course> createCourse(CourseCreateRequest courseCreateRequest) {
        Admin admin = adminService.findById(courseCreateRequest.getAdminId());
        if(admin == null){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Admin not found");
        }
        Course course = new Course();
        course.setAdminId(courseCreateRequest.getAdminId());
        course.setTitle(courseCreateRequest.getTitle());
        course.setDescription(courseCreateRequest.getDescription());
        course.setPrice(courseCreateRequest.getPrice());
        course.getImages().addAll(courseCreateRequest.getImages());
        course.setMode(courseCreateRequest.getMode());
        course.setLevel(courseCreateRequest.getLevel());
        course.getTopics().addAll(courseCreateRequest.getTopics());
        course.setEnrollmentStartDate(courseCreateRequest.getEnrollmentStartDate());
        course.setEnrollmentEndDate(courseCreateRequest.getEnrollmentEndDate());
        course.setCourseStartDate(courseCreateRequest.getCourseStartDate());
        course.setCourseEndDate(courseCreateRequest.getCourseEndDate());
        course.setCertificateAvailable(courseCreateRequest.isCertificateAvailable());
        course.setMaxEnrollment(courseCreateRequest.getMaxEnrollment());
        course.setStatus(courseCreateRequest.getStatus());
        course.setCreatedAt(LocalDateTime.now());
        return new ApiResponse<>(courseRepository.save(course), HttpStatus.CREATED);
    }

    @Override
    public ApiResponse<List<Course>> fetchAllCourse() {
        return new ApiResponse<>(courseRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public boolean addBatchInCourse(String batchId, String courseId) {
        Course course = findById(courseId);
        course.getBatchIds().add(batchId);
        courseRepository.save(course);
        return true;
    }

    @Override
    public Course findById(String courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }
}
