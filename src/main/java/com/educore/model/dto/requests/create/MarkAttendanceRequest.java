package com.educore.model.dto.requests.create;

import com.educore.model.entity.Attendance;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MarkAttendanceRequest {
    private List<Attendance> attendances;
    private String sessionId;
    private String markedBy;
}
