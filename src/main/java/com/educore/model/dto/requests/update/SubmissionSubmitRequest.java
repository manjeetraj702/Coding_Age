package com.educore.model.dto.requests.update;

import com.educore.model.dto.requests.create.AnswerDetailModel;
import com.educore.model.enums.SubmissionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SubmissionSubmitRequest {
    private List<AnswerDetailModel> answerDetailModelList;
    private String testSubmissionId;
    private String studentId;
    private SubmissionStatus status;
}
