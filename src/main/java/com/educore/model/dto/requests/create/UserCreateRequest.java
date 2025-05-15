package com.educore.model.dto.requests.create;

import com.educore.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role;
}
