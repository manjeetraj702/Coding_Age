package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.QuestionCreateRequest;
import com.educore.model.entity.Question;
import com.educore.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Question>> create(@RequestBody QuestionCreateRequest createRequest){
        ApiResponse<Question> response = questionService.create(createRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Question>>> fetchAll(){
        ApiResponse<List<Question>> response = questionService.fetchAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/fetch/type")
    public ResponseEntity<ApiResponse<List<Question>>> fetchAllByType(@RequestParam String type){
        ApiResponse<List<Question>> response = questionService.fetchAllByType(type);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @GetMapping("/fetch/by/ids")
    public ResponseEntity<ApiResponse<List<Question>>> fetchAllByIds(@RequestParam Set<String> ids){
        ApiResponse<List<Question>> response = questionService.fetchAllByIds(ids);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
