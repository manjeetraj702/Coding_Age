package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.LiveTestCreateRequest;
import com.educore.model.dto.response.LiveTestDto;
import com.educore.service.LiveTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/live_test")
@CrossOrigin(origins = "*")
public class LiveTestController {

    @Autowired
    private LiveTestService liveTestService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<LiveTestDto>> createLiveTest(@RequestBody LiveTestCreateRequest createRequest){
        ApiResponse<LiveTestDto> response = liveTestService.createLiveTest(createRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

//    @GetMapping("/fetch")
//    public ResponseEntity<ApiResponse<Page<LiveTestDto>>> fetchLiveTest(@RequestBody QuizFetchRequest fetchRequest){
//       ApiResponse<Page<LiveTestDto>> response = liveTestService.fetchLiveTest(fetchRequest);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }

    @GetMapping("/fetch/all")
    public ResponseEntity<ApiResponse<List<LiveTestDto>>> fetchAllTests(){
        ApiResponse<List<LiveTestDto>> response = liveTestService.fetchAllLiveTest();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{studentId}/not-attempted")
    public ResponseEntity<ApiResponse<List<LiveTestDto>>> fetchAllNotAttempted(@PathVariable String studentId){
        ApiResponse<List<LiveTestDto>> response = liveTestService.fetchAllUnAttempted(studentId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{studentId}/attempt/{testId}")
    public ResponseEntity<ApiResponse<Boolean>> attemptQuiz(@PathVariable String studentId, @PathVariable String testId){
        ApiResponse<Boolean> response = liveTestService.attemptLiveTest(studentId, testId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
//    @PutMapping("/update/{quizId}")
//    public ResponseEntity<ApiResponse<Boolean>> updateLiveTest(@RequestParam String testId, @RequestBody QuizUpdateRequest updateRequest){
//        ApiResponse<Boolean> response = liveTestService.updateLiveTest(testId, updateRequest);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }

}
