package com.educore.model.dto.requests.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequest {

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Name Field is Empty")
    private String name;

    @NotBlank(message = "Email Field is Empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone Field is Empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number Invalid")
    private String phoneNumber;

    @NotBlank(message = "Password Field is Empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;
}
