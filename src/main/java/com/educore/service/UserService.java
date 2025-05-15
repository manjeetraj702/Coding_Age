package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.UserCreateRequest;
import com.educore.model.entity.Address;
import com.educore.model.entity.User;

import java.util.List;

public interface UserService {
    User create(UserCreateRequest userCreateRequest);

    User fetchByPhoneNumber(String phoneNumber);

    User findById(String userId);

    List<User> getAllUser();

    User addAddress(String id, Address address);

    void updateLastSignIn(String id);

    Boolean updatePassword(String id, String oldPassword, String newPassword);

    ApiResponse<Boolean> updateAddress(String id, Address address);

    Boolean updateOtp(int otp, String userId);

    ApiResponse<Boolean> updateEmail(String id, String email);

    ApiResponse<Boolean> updateMobileNumber(String id, String mobNo);
}
