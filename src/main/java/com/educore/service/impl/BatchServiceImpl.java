package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.BatchCreateRequest;
import com.educore.model.entity.*;
import com.educore.repository.BatchRepository;
import com.educore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private AdminService adminService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private BatchRepository batchRepository;

    @Override
    public ApiResponse<Batch> createBatch(BatchCreateRequest batchCreateRequest) {
        Course course = courseService.findById(batchCreateRequest.getCourseId());
        if(course == null){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Course Not Found");
        }
        Employee employee = employeeService.findById(batchCreateRequest.getCreatorId());
        if(employee != null){
            Batch batch = batchRepository.save(create(batchCreateRequest));
            courseService.addBatchInCourse(batch.getId(), batch.getCourseId());
            return new ApiResponse<>(batch, HttpStatus.CREATED);
        }else {
            Admin admin = adminService.findById(batchCreateRequest.getCreatorId());
            if(admin != null){
                Batch batch = batchRepository.save(create(batchCreateRequest));
                courseService.addBatchInCourse(batch.getId(), batch.getCourseId());
                return new ApiResponse<>(batch, HttpStatus.CREATED);
            }else {
                return new ApiResponse<>(HttpStatus.NOT_FOUND, "Creator User not found");
            }
        }
    }

    private Batch create(BatchCreateRequest batchCreateRequest){
        Batch batch = new Batch();
        batch.setCreatorId(batchCreateRequest.getCreatorId());
        batch.setBatchName(batchCreateRequest.getBatchName());
        batch.setCourseId(batchCreateRequest.getCourseId());
        batch.setStartDate(batchCreateRequest.getStartDate());
        batch.setTiming(batchCreateRequest.getTiming());
        batch.setMaxStudent(batchCreateRequest.getMaxStudent());
        batch.setStatus(batchCreateRequest.getStatus());
        batch.setCreatedAt(LocalDateTime.now());
        return batch;
    }

    @Override
    public ApiResponse<Boolean> enrollStudent(String batchId, String studentId) {
        Batch batch = Optional.ofNullable(findById(batchId)).orElseThrow(
                () -> new ApplicationException("Batch not found"));

        Optional.ofNullable(studentService.findById(studentId)).orElseThrow(
                () -> new ApplicationException("Student not found"));

        if(batch.getStudentsIds().contains(studentId)){
            return new ApiResponse<>(HttpStatus.CONFLICT, "Student Already Enrolled in Batch");
        }
        batch.getStudentsIds().add(studentId);
        batchRepository.save(batch);
        studentService.enrollInBatch(batchId, studentId, batch.getCourseId());
        return new ApiResponse<>(true, HttpStatus.OK);
    }

    @Override
    public ApiResponse<Boolean> removeStudent(String batchId, String studentId) {
        Batch batch = Optional.ofNullable(findById(batchId)).orElseThrow(
                () -> new ApplicationException("Batch not found"));

        Optional.ofNullable(studentService.findById(studentId)).orElseThrow(
                () -> new ApplicationException("Student not found"));

        if(batch.getStudentsIds().contains(studentId)){
            batch.getStudentsIds().remove(studentId);
            batchRepository.save(batch);
            studentService.removeFromBatch(batchId, studentId);
            return new ApiResponse<>(true, HttpStatus.OK);
        }else {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Student Already Not Enrolled in Batch");
        }

    }

    @Override
    public ApiResponse<List<Batch>> fetchAllBatches() {
        return new ApiResponse<>(batchRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ApiResponse<Batch> fetchBatch(String batchId) {
        Batch batch = findById(batchId);
        if(batch != null){
            return new ApiResponse<>(batch, HttpStatus.OK);
        }else {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Batch not found");
        }
    }

    @Override
    public ApiResponse<List<Batch>> fetchBatchesByIds(Set<String> batchIds) {
        if (batchIds.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "You are not Enrolled in Batch, Go get Enrolled");
        }
        Set<String> ids = batchIds.stream()
                .map(id -> id.replaceAll("[{}]", "").trim())
                .filter(id -> !id.isEmpty())
                .collect(Collectors.toSet());
        List<Batch> batches = batchRepository.findAllById(ids);

        if (batches.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "No batches found");
        }

        return new ApiResponse<>(batches, HttpStatus.OK);
    }

    @Override
    public void markSession(String batchId, String id) {
        Batch batch = Optional.ofNullable(findById(batchId)).orElseThrow(
                () -> new ApplicationException("Batch id not found"));
        batch.setLastSessionDate(LocalDate.now());
        batch.getSessionsIds().add(id);
        batchRepository.save(batch);
    }

    @Override
    public Batch findById(String id) {
        return batchRepository.findById(id).orElse(null);
    }

}
