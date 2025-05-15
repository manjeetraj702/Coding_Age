package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.ContentCreateRequest;
import com.educore.model.dto.response.ContentDTO;
import com.educore.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PreAuthorize("hasAnyAuthority('ADMIN',  'EMPLOYEE')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ContentDTO>> createCourse(@RequestBody ContentCreateRequest createRequest){
        ApiResponse<ContentDTO> response = contentService.createContent(createRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/fetch/{category}")
    public ResponseEntity<ApiResponse<List<ContentDTO>>> fetchContents(@PathVariable String category){
        ApiResponse<List<ContentDTO>> response = contentService.fetchContents(category);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
