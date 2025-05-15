package com.educore.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Option {
    private String answer;
    private boolean isCorrect;
}
