package com.educore.model.dto.requests.update;

import com.educore.model.enums.Difficulty;
import com.educore.model.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class QuizUpdateRequest {
    private String title;

    private String description;

    private Set<String> questionIds;

    private String subject;

    private String topic;

    private Level level;

    private Difficulty difficulty;
}
