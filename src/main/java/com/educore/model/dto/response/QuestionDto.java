package com.educore.model.dto.response;

import com.educore.model.enums.Difficulty;
import com.educore.model.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private String id;

    private Type type;

    private Difficulty difficulty;

    private String subject;

    private String topic;

    private String content;

    private List<Option> options;

    private String description;

    private int totalNoOfSolved;

    private int totalNoOfUnsolved;
}
