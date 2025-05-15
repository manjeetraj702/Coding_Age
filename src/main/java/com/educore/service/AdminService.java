package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.SignUpRequest;
import com.educore.model.entity.Admin;
import com.educore.model.entity.User;

public interface AdminService {
    Admin create(SignUpRequest signUpRequest);

    Admin findByUser(User user);

    Admin findById(String modelId);

    ApiResponse<Admin> fetchById(String id);
}
