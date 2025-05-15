package com.educore.model.dto.requests.create;

import com.educore.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
public class BatchCreateRequest {
    private String creatorId;

    private String batchName;

    private String courseId;

    private LocalDate startDate;

    private LocalTime timing;

    private int maxStudent;

    private Status status;
}
