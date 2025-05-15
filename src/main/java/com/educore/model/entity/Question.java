package com.educore.model.entity;

import com.educore.model.dto.response.Option;
import com.educore.model.enums.Difficulty;
import com.educore.model.enums.Type;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "questions")
public class Question {
    @Id
    private String id;

    @Indexed
    private Type type;

    @Indexed
    private Difficulty difficulty;

    @Indexed
    private String subject;

    @Indexed
    private String topic;

    private String content;

    private List<Option> options;

    private String description;

    private int totalNoOfSolved;

    private int totalNoOfUnsolved;

    private String createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}