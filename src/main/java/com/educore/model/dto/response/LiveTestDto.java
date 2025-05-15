package com.educore.model.dto.response;

import com.educore.model.enums.Difficulty;
import com.educore.model.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LiveTestDto {
    private String id;

    private String title;

    private String description;

    private int participants;

    private Set<String> questionIds;

    private String subject;

    private String topic;

    private Level level;

    private Difficulty difficulty;

    private Duration duration;

}
