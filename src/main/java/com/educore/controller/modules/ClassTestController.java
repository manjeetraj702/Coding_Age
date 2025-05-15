package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.TestCreateRequest;
import com.educore.model.entity.ClassTest;
import com.educore.service.ClassTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/test")
public class ClassTestController {

    @Autowired
    private ClassTestService classTestService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ClassTest>> createTest(@RequestBody TestCreateRequest testCreateRequest){
        ApiResponse<ClassTest> response = classTestService.createTest(testCreateRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/join")
    public ResponseEntity<ApiResponse<Boolean>> joinTest(@RequestParam String testId, @RequestParam String studentId){
        ApiResponse<Boolean> response = classTestService.joinTest(testId, studentId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/all/by/{batchId}")
    public ResponseEntity<ApiResponse<List<ClassTest>>> getTestsOfBatch(@PathVariable String batchId){
        ApiResponse<List<ClassTest>> response = classTestService.getTestsOfBatch(batchId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
