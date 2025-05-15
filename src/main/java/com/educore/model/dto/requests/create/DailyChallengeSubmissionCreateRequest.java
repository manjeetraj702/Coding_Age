package com.educore.model.dto.requests.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyChallengeSubmissionCreateRequest {
    private String dailyChallengeId;

    private String studentId;

    private String answer;

    private boolean isCorrect;
}
