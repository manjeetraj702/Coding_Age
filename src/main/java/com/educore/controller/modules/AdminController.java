package com.educore.controller.modules;

import com.educore.common.utils.ApiResponse;
import com.educore.model.entity.Admin;
import com.educore.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Admin>> fetchById(@PathVariable String id){
        ApiResponse<Admin> response = adminService.fetchById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
