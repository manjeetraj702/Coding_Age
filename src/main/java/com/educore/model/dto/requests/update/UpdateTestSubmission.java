package com.educore.model.dto.requests.update;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateTestSubmission {
    private String id;
    private String evaluatorId;
    private List<UpdateAnswerDetailModel> answerDetailList;
    private String feedback;
}
