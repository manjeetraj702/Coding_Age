package com.educore.model.dto.response;

import com.educore.model.enums.SubmissionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentResult {
    private String name;
    private double obtainedMark;
    private SubmissionStatus status;
}
