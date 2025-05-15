package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.SignUpRequest;
import com.educore.model.dto.requests.create.UserCreateRequest;
import com.educore.model.entity.Admin;
import com.educore.model.entity.User;
import com.educore.model.enums.Role;
import com.educore.repository.AdminRepository;
import com.educore.service.AdminService;
import com.educore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;

    @Override
    public Admin create(SignUpRequest signUpRequest) {
        User user = userService.create(new UserCreateRequest(
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getPhoneNumber(),
                signUpRequest.getPassword(),
                Role.ADMIN)
        );
        Admin admin = new Admin();
        admin.setUser(user);
        return adminRepository.save(admin);
    }

    @Override
    public Admin findByUser(User user) {
        return adminRepository.findByUser(user);
    }

    @Override
    public Admin findById(String modelId) {
        return adminRepository.findById(modelId).orElse(null);
    }

    @Override
    public ApiResponse<Admin> fetchById(String id) {
        Admin admin = findById(id);
        if(admin == null){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Admin not found");
        }
        return new ApiResponse<>(admin ,HttpStatus.OK);
    }
}
