package com.educore.service.impl;

import com.educore.service.TwilioService;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioServiceImpl implements TwilioService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.verify.sid}")
    private String verifySid;

    @Override
    public void sendOtp(String phoneNumber) {
        Twilio.init(accountSid, authToken);
        Verification.creator(
                verifySid,
                phoneNumber,
                "sms"
        ).create();
    }

    @Override
    public boolean verifyOtp(String phoneNumber, String otp) {
        Twilio.init(accountSid, authToken);
        VerificationCheck verification = VerificationCheck.creator(
                        verifySid
                )
                .setTo(phoneNumber)
                .setCode(otp)
                .create();

        return "approved".equals(verification.getStatus());
    }
}
