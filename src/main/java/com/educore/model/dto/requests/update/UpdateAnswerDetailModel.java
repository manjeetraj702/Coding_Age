package com.educore.model.dto.requests.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAnswerDetailModel {
    private String id;
    private double marksObtained;
    private boolean isCorrect;
    private String evaluatorComments;
}
