package com.educore.model.dto.requests.create;

import com.educore.model.enums.Difficulty;
import com.educore.model.enums.Level;
import com.educore.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class LiveTestCreateRequest {
    private String title;

    private String description;

    private Set<String> questionIds;

    private String subject;

    private String topic;

    private Level level;

    private Difficulty difficulty;

    private Duration duration;

    private String createdBy;

    private Role creatorRole;
}
