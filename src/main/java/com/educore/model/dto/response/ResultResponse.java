package com.educore.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResultResponse {
    private String topic;
    private LocalDate date;
    private String batchName;
    private String topper;
    private double totalMarks;
    private List<StudentResult> submissions;
}
