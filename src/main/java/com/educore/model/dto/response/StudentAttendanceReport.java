package com.educore.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StudentAttendanceReport {
    private List<DailyAttendanceReport> dailyAttendanceReports;
    private int noOfAbsents;
    private int noOfPresents;
    private int noOfLeaves;
    private int totalSessions;
}
