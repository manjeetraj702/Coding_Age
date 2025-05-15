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
@Document(collection = "assessments_submissions")
public class AssessmentSubmissions {
    @Id
    private String id;

    private String assessmentId;

    private String userId;

    private int attempt;

    private int wrong;

    private int correct;

    private int skipped;

    private double marks;

    private double score;

    private LocalDateTime attemptedTime; // this submission  will auto delete after 15 days make that API

    private String remarks;
}
