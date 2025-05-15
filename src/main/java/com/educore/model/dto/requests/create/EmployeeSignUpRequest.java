package com.educore.model.dto.requests.create;

import com.educore.model.entity.Address;
import com.educore.model.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeSignUpRequest {
    private SignUpRequest credential;

    private Department department;

    private LocalDate joiningDate;

    private Address address;

    //    private String jobId;  ///Job id will created by Backend
}
