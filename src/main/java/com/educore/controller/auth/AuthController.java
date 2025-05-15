package com.educore.controller.auth;
import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.EmployeeSignUpRequest;
import com.educore.model.dto.requests.create.LoginRequest;
import com.educore.model.dto.requests.create.SignUpRequest;
import com.educore.model.dto.requests.create.StudentSignupRequest;
import com.educore.model.dto.response.AuthResponse;
import com.educore.model.entity.Admin;
import com.educore.model.entity.Employee;
import com.educore.model.entity.Student;
import com.educore.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    // This signs up API for new Users without any linked with our institute
    @PostMapping("/signup")
    public ApiResponse<Student> signUp(@Valid @RequestBody SignUpRequest signUpRequest){
        return authService.signUp(signUpRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN, 'EMPLOYEE'')")
    @PostMapping("/student-signup")
    public ApiResponse<Student> studentSignUp(@Valid @RequestBody StudentSignupRequest signUpRequest){
        return authService.studentSignUp(signUpRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/employee-signup")
    public ApiResponse<Employee> employeeSignUp(@Valid @RequestBody EmployeeSignUpRequest signUpRequest){
        return authService.employeeSignUp(signUpRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin-signup")
    public ApiResponse<Admin> adminSignUp(@Valid @RequestBody SignUpRequest signUpRequest){
        return authService.adminSignUp(signUpRequest);
    }

    @PutMapping("/{id}/password-update")
    public ResponseEntity<ApiResponse<Boolean>> passwordUpdate(@PathVariable String id,
       @RequestParam String oldPassword, @RequestParam String newPassword){
        ApiResponse<Boolean> response = authService.updatePassword(id, oldPassword, newPassword);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/refresh-token")
    public ApiResponse<AuthResponse> refreshToken(@RequestBody Map<String, String> request) {
        return authService.refreshToken(request);
    }

}
