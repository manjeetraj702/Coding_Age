package com.educore.model.dto.requests.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestSubmissionModel {
    @NotBlank(message = "Test ID is Empty")
    private String testId;
    @NotBlank(message = "Student ID is Empty")
    private String studentId;

    private String name;
}
