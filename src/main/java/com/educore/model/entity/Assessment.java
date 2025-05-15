package com.educore.model.entity;

import com.educore.model.enums.AssessmentType;
import com.educore.model.enums.Category;
import com.educore.model.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "assessments")
public class Assessment {
    @Id
    private String id;

    private String title;

    private String description;

    private int totalAttempts;

    @Indexed
    private Set<String> attemptedBy = new HashSet<>();

    private Duration duration;

    private Set<String> questionsId;

    @Indexed
    private Level level;

    @Indexed
    private AssessmentType type;

    @Indexed
    private Category category;

    private String createdBy;

    private LocalDateTime endingAt;

    private boolean active;

    private LocalDateTime createdAt; // If this is Live test then it should be deleted
                                     // after deadline or not showing in UI
    private LocalDateTime updatedAt;
}
