package com.educore.model.entity;

import com.educore.model.enums.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    private String studentId;

    private String studentName;

    private AttendanceStatus status;

    private String remarks;  // Extra notes (e.g., "Medical Leave", "Late Arrival")

   // private LocalDateTime markedAt;  // Timestamp when attendance was marked, Currently this is not needed
}
