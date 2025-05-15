package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.SubjectCreateRequest;
import com.educore.model.entity.Subject;
import com.educore.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
@CrossOrigin(origins = "*")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Subject>> createSubject(@RequestBody SubjectCreateRequest createRequest){
        ApiResponse<Subject> response = subjectService.createSubject(createRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/fetch")
    public ResponseEntity<ApiResponse<List<Subject>>> fetchSubjects(){
        ApiResponse<List<Subject>> response = subjectService.fetchSubjects();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PutMapping("/{id}/remove-topic/{index}")
    public ResponseEntity<ApiResponse<Boolean>> removeTopic(@PathVariable String id,  @PathVariable int index){
        ApiResponse<Boolean> response = subjectService.removeTopic(id, index);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PutMapping("/{id}/add-topic")
    public ResponseEntity<ApiResponse<Boolean>> addTopic(@PathVariable String id,  @RequestParam String topic){
        ApiResponse<Boolean> response = subjectService.addTopic(id, topic);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteSubject(@PathVariable String id){
        ApiResponse<Boolean> response = subjectService.deleteSubject(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
