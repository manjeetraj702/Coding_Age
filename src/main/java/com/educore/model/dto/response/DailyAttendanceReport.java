package com.educore.model.dto.response;

import com.educore.model.enums.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DailyAttendanceReport {
    private LocalDateTime sessionDate;
    private AttendanceStatus status;
    private String remarks;
}
