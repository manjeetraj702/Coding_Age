package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.CourseCreateRequest;
import com.educore.model.entity.Course;

import java.util.List;

public interface CourseService {

    Course findById(String courseId);

    ApiResponse<Course> createCourse(CourseCreateRequest courseCreateRequest);

    ApiResponse<List<Course>> fetchAllCourse();

    boolean addBatchInCourse(String batchId, String courseId);
}
