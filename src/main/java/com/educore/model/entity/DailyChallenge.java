package com.educore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "daily_challenge")
public class DailyChallenge {
    @Id
    private String id;

    private String question;

    private String description;

    private String createdBy;

    private int noOfSubmissions;

    private Set<String> studentsId = new HashSet<>();

    private Set<String> submissionsId = new HashSet<>();

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;
}
