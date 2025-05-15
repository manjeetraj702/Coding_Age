package com.educore.service;

import com.educore.model.dto.requests.create.AnswerDetailModel;
import com.educore.model.dto.requests.update.UpdateAnswerDetailModel;
import com.educore.model.entity.AnswerDetail;

public interface AnswerDetailService {
    AnswerDetail createAnswerDetail(AnswerDetailModel answerDetailModel , String submissionId);

    AnswerDetail evaluateAnswer(UpdateAnswerDetailModel model);

    AnswerDetail findById(String id);
}
