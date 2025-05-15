package com.educore.service;

public interface TwilioService {

    void  sendOtp(String phoneNumber);

    boolean verifyOtp(String phoneNumber, String otp); // change into dto
}
