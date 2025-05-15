package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.model.dto.requests.create.EmailRequest;
import com.educore.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public ApiResponse<String> sendOtp(EmailRequest request) {
        try {
            int otp = 1000 + new Random().nextInt(9999);
            String name;
            if(request.getUserName() == null || request.getUserName().isEmpty()){
                name = "User";
            }else {
                name = request.getUserName();
            }

            String htmlBody = getHtmlTemplate(otp+"", name);
            String plainTextBody = getPlainTextTemplate(otp+"", name);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("gdadvertising2020@gmail.com", "Coding Age");
            helper.setReplyTo("gdadvertising2020@gmail.com");
            helper.setTo(request.getTo());
            helper.setSubject("Your Coding Age Verification Code");
            helper.setText(plainTextBody, htmlBody); // both plain text and HTML
            mailSender.send(message);
            return new ApiResponse<>(otp+"", HttpStatus.OK);
        } catch (MessagingException | UnsupportedEncodingException e) {
            return new ApiResponse<>(null, HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private String getHtmlTemplate(String otp, String name) {
        return """
<!DOCTYPE html>
<html>
<body style="font-family: Arial, sans-serif; background-color: #f9f9f9; padding: 20px;">
  <div style="max-width: 500px; margin: auto; background-color: #fff; padding: 30px; border-radius: 10px; box-shadow: 0 0 5px rgba(0,0,0,0.1);">
    <h2 style="color: #333333;">Coding Age Verification Code</h2>
    <p>Dear %s,</p>
    <p><strong>%s</strong> is your verification code. Please do not disclose it to anyone.</p>
    <p>This code is valid for 10 minutes.</p>
    <p style="font-size: 12px; color: #aaa;">Please do not reply to this email.</p>
    <p>Best Regards,<br><strong>Coding Age Support Team</strong></p>
  </div>
</body>
</html>
""".formatted(name, otp);
    }


    private String getPlainTextTemplate(String otp, String name) {
        return """
    Coding Age OTP Verification

    Hi %s,
    
    Your OTP is: %s

    This OTP is valid for 10 minutes.

    If you didn’t request this, you can safely ignore this email.
    """.formatted(name, otp);
    }
}
