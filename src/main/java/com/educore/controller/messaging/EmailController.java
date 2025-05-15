package com.educore.controller.messaging;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.EmailRequest;
import com.educore.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@CrossOrigin(origins = "*")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<String>> sendOtp(@RequestBody EmailRequest request) {
        ApiResponse<String> response = emailService.sendOtp(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
