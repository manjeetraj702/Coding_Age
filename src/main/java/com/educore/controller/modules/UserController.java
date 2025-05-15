package com.educore.controller.modules;


import com.educore.common.utils.ApiResponse;
import com.educore.model.entity.Address;
import com.educore.model.entity.User;
import com.educore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/fetch")
    public ApiResponse<List<User>> getAllUser(){
        return new ApiResponse<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PutMapping("/{id}/update-address")
    public ResponseEntity<ApiResponse<Boolean>> updateAddress(@PathVariable String id, @RequestBody Address address){
        ApiResponse<Boolean> response = userService.updateAddress(id, address);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}/email-update")
    public ResponseEntity<ApiResponse<Boolean>> updateEmail(@PathVariable String id, @RequestParam String email){
        ApiResponse<Boolean> response = userService.updateEmail(id, email);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}/mob-update")
    public ResponseEntity<ApiResponse<Boolean>> updateMobileNumber(@PathVariable String id, @RequestParam String newMobileNumber){
        ApiResponse<Boolean> response = userService.updateMobileNumber(id, newMobileNumber);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
