package com.educore.model.entity;

import com.educore.model.enums.Difficulty;
import com.educore.model.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "live_test")
@CompoundIndexes({@CompoundIndex(name = "subject_topic_idx", def = "{'subject': 1, 'topic': 1}")})
public class LiveTest {
    @Id
    private String id;

    private String title;

    private String description;

    private Set<String> attemptedBy;

    private int participants;

    private Set<String> questionIds;

    @Indexed
    private String subject;

    @Indexed
    private String topic;

    private Level level;

    private Difficulty difficulty;

    private String createdBy;

    private boolean isActive;

    private Duration duration;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public LiveTest(){
        this.attemptedBy = new HashSet<>();
        this.questionIds = new HashSet<>();
        this.createdAt = LocalDateTime.now();
    }
}
