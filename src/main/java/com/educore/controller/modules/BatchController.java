package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.BatchCreateRequest;
import com.educore.model.entity.Batch;
import com.educore.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Batch>> createBatch(@RequestBody BatchCreateRequest batchCreateRequest){
        ApiResponse<Batch> response = batchService.createBatch(batchCreateRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PutMapping("/enroll-student")
    public ResponseEntity<ApiResponse<Boolean>> enrollStudentInBatch(
            @RequestParam String batchId, @RequestParam String studentId){
        ApiResponse<Boolean> response = batchService.enrollStudent(batchId, studentId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PutMapping("/remove-student")
    public ResponseEntity<ApiResponse<Boolean>> removeStudentFromBatch(
            @RequestParam String batchId, @RequestParam String studentId){
        ApiResponse<Boolean> response = batchService.removeStudent(batchId, studentId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/by/{id}")
    public ResponseEntity<ApiResponse<Batch>> fetchBatch(@PathVariable String id){
        ApiResponse<Batch> response = batchService.fetchBatch(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/by/Ids")
    public ResponseEntity<ApiResponse<List<Batch>>> fetchBatchesByIds(@RequestParam Set<String> batchIds){
        ApiResponse<List<Batch>> response = batchService.fetchBatchesByIds(batchIds);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Batch>>> fetchAllBatches(){
        ApiResponse<List<Batch>> response = batchService.fetchAllBatches();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
