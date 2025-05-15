package com.educore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "sessions")
public class Session {
    @Id
    private String id;

    private String batchId;

    private String topic;

    private String creatorId; // ID of employee/Admin who create the session

    private String instructorName;

    private LocalDate sessionDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private List<Attendance> attendanceRecord;

    private int noOfAbsent;

    private int noOfPresent;

    private String attendanceMarkedBy; // ID of employee/Admin who marked the attendance

    private List<String> sessionRecordingUrl; // This should be single URL because one session does not have multiple
                                                // Session Recording URl
    private List<String> practiceSets;

    private List<String> notes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Session(){
        this.attendanceRecord = new ArrayList<>();
        this.sessionRecordingUrl = new ArrayList<>();
        this.practiceSets = new ArrayList<>();
        this.notes = new ArrayList<>();
    }
}
