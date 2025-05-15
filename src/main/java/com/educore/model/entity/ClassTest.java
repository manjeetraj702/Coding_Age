package com.educore.model.entity;

import com.educore.model.enums.Category;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "class_tests")
public class ClassTest {
    @Id
    private String id;

    @Field(name = "topic_name")
    private String topicName;

    @Field(name = "test_description")
    private String testDescription;

    private String examinerId;

    private String examinerName;

    @Field(name = "test_duration")
    private Duration testDuration;

    @Field(name = "starting_time")
    private LocalTime startingTime;

    @Field(name = "ending_time")
    private LocalTime endingTime;

    private LocalDate date;

    @Field(name = "joining_id")
    private String joiningId;

    @Field(name = "students_id")
    private List<String> studentsId;

    private List<Question> questions;

    @Field(name = "is_complete")
    private boolean isComplete;

    @Field(name = "batch_id")
    private String batchId;

    private Category category;

    private String createdBy;

    public ClassTest(String topicName, String testDescription,
                     Duration testDuration, LocalTime startingTime,
                     LocalTime endingTime, LocalDate date, String examinerId) {
        this.topicName = topicName;
        this.testDescription = testDescription;
        this.testDuration = testDuration;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.date = date;
        this.examinerId = examinerId;
        this.studentsId = new ArrayList<>();
        this.questions = new ArrayList<>();
    }
}
