package com.educore.model.entity;

import com.educore.model.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "users")
public class User{
    @Id
    private String id;

    private String uid; // required when signup with Google

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Name Field is Empty")
    private String name;

    @Indexed(unique = true)
    @NotBlank(message = "Email Field is Empty")
    @Email(message = "Invalid email format")
    private String email;

    @Indexed(unique = true)
    @NotBlank(message = "Phone Field is Empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number Invalid")
    private String phoneNumber;

    @NotBlank(message = "Password Field is Empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;

    @NotNull(message = "Role is required")
    @Field(name = "user_role")
    private Role role;

    private Address address;

    // Verification Details

    //extra data
    private String oldEmail;
    private String oldPhoneNumber;
    private LocalDateTime emailConfirmedAt;
    private LocalDateTime phoneConfirmedAt;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
    private LocalDateTime lastSignedAt;

    public User(String name, String email, String phoneNumber, String password, Role role) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }
}
