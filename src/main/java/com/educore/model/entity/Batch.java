package com.educore.model.entity;

import com.educore.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "batches")
public class Batch {
    @Id
    private String id;

    private String batchName;

    @Indexed
    private String courseId;

    @Indexed
    private String creatorId;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime timing;

    private LocalDate lastSessionDate;

    private Set<String> sessionsIds = new HashSet<>(); // sessions Ids

    private Set<String> studentsIds = new HashSet<>(); // students Ids

    private int maxStudent;

    @Indexed
    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
