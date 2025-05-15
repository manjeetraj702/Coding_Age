package com.educore.model.dto.requests.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class StudentAttendanceReportRequest {
    private String batchId;
    private String studentId;
    private LocalDate fromDate;
    private LocalDate toDate;
}
