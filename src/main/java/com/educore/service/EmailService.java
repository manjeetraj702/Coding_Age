package com.educore.service;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.EmailRequest;

public interface EmailService {

    ApiResponse<String> sendOtp(EmailRequest request);
}
