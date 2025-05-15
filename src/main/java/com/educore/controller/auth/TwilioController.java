package com.educore.controller.auth;

import com.educore.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verification")
@CrossOrigin(origins = "*")
public class TwilioController {

    @Autowired
    private TwilioService twilioService;

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam String phone) {
        twilioService.sendOtp(phone);
        return ResponseEntity.ok("OTP Sent");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String phone, @RequestParam String otp) {
        boolean result = twilioService.verifyOtp(phone, otp);
        if (result) {
            return ResponseEntity.ok("OTP Verified Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("OTP Verification Failed");
        }
    }
}
