package com.educore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "daily_challenge_submission")
public class DailyChallengeSubmission {
    @Id
    private String id;

    private String dailyChallengeId;

    private String studentId;

    private String answer;

    private boolean isCorrect;

    private LocalDateTime submittedAt = LocalDateTime.now();
}
