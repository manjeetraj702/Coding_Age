package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.QuizCreateRequest;
import com.educore.model.dto.requests.fetch.QuizFetchRequest;
import com.educore.model.dto.requests.update.QuizUpdateRequest;
import com.educore.model.dto.response.QuizDto;
import com.educore.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/quiz")
@CrossOrigin(origins = "*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<QuizDto>> createQuiz(@RequestBody QuizCreateRequest createRequest){
        ApiResponse<QuizDto> response = quizService.createQuiz(createRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/fetch")
    public ResponseEntity<ApiResponse<Page<QuizDto>>> fetchQuiz(@RequestBody QuizFetchRequest fetchRequest){
        ApiResponse<Page<QuizDto>> response = quizService.fetchQuiz(fetchRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/fetch/all")
    public ResponseEntity<ApiResponse<List<QuizDto>>> fetchAllQuiz(){
        ApiResponse<List<QuizDto>> response = quizService.fetchAllQuiz();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{studentId}/attempt/{quizId}")
    public ResponseEntity<ApiResponse<Boolean>> attemptQuiz(@PathVariable String studentId, @PathVariable String quizId){
        ApiResponse<Boolean> response = quizService.attemptQuiz(studentId, quizId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PutMapping("/update/{quizId}")
    public ResponseEntity<ApiResponse<Boolean>> updateQuiz(@RequestParam String quizId, @RequestBody QuizUpdateRequest updateRequest){
        ApiResponse<Boolean> response = quizService.updateQuiz(quizId, updateRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
