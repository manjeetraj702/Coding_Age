package com.educore.model.dto.requests.update;

import com.educore.model.entity.Question;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class UpdateTestModel {
    private String topicName;
    private String testDescription;
    private LocalTime startingTime;
    private LocalTime endingTime;
    private LocalDateTime date;
    private String examiner;
    private List<Question> questions;
}
