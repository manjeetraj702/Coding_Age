package com.educore.service.impl;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.AnswerDetailModel;
import com.educore.model.dto.requests.update.UpdateAnswerDetailModel;
import com.educore.model.entity.AnswerDetail;
import com.educore.repository.AnswerDetailRepository;
import com.educore.service.AnswerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AnswerDetailServiceImpl implements AnswerDetailService {

    @Autowired
    private AnswerDetailRepository answerDetailRepository;

    @Override
    public AnswerDetail createAnswerDetail(AnswerDetailModel answerDetailModel, String submissionId) {
        AnswerDetail answerDetail = new AnswerDetail();
        answerDetail.setTestSubmissionId(submissionId);
        answerDetail.setQuestion(answerDetailModel.getQuestion());
        answerDetail.setAnswer(answerDetailModel.getAnswer());
        return answerDetailRepository.save(answerDetail);
    }

    @Override
    public AnswerDetail evaluateAnswer(UpdateAnswerDetailModel model) {
        AnswerDetail answerDetail = findById(model.getId());
        if(answerDetail == null){
            throw new ApplicationException("Answer Detail of this ID " + model.getId() + " not found");
        }
        answerDetail.setCorrect(model.isCorrect());
        answerDetail.setMarksObtained(model.getMarksObtained());
        answerDetail.setEvaluatorComment(model.getEvaluatorComments());
        return answerDetailRepository.save(answerDetail);

    }

    @Override
    public AnswerDetail findById(String id) {
        return answerDetailRepository.findById(id).orElse(null);
    }
}

