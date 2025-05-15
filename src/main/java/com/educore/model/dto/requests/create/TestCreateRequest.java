package com.educore.model.dto.requests.create;

import com.educore.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TestCreateRequest {
    private String topic;
    private String batchId;
    private String description;
    private Category category;
    private LocalTime startTime;
    private LocalTime endTime;
    private String examinerId;
    private String examinerName;
    private String joiningId;
    private List<String> questionsId;
}
