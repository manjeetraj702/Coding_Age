package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.AnswerDetailModel;
import com.educore.model.dto.requests.create.TestSubmissionModel;
import com.educore.model.dto.requests.update.SubmissionSubmitRequest;
import com.educore.model.dto.requests.update.UpdateAnswerDetailModel;
import com.educore.model.dto.requests.update.UpdateTestSubmission;
import com.educore.model.dto.response.ResultResponse;
import com.educore.model.entity.AnswerDetail;
import com.educore.model.entity.ClassTest;
import com.educore.model.entity.TestSubmission;
import com.educore.model.enums.SubmissionStatus;
import com.educore.repository.TestSubmissionRepository;
import com.educore.service.AnswerDetailService;
import com.educore.service.StudentService;
import com.educore.service.ClassTestService;
import com.educore.service.TestSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestSubmissionServiceImpl implements TestSubmissionService {

    @Autowired
    private TestSubmissionRepository testSubmissionRepository;

    @Autowired
    private AnswerDetailService answerDetailService;

    @Autowired
    private ClassTestService classTestService;

    @Autowired
    private StudentService studentService;

    @Override
    public ApiResponse<TestSubmission> create(TestSubmissionModel testSubmissionModel) {
        if(classTestService.findById(testSubmissionModel.getTestId()) == null){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "ClassTest not found");
        }
        if(studentService.findById(testSubmissionModel.getStudentId()) == null){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Student not found");
        }
        TestSubmission testSubmission = new TestSubmission();
        testSubmission.setTestId(testSubmissionModel.getTestId());
        testSubmission.setStudentId(testSubmissionModel.getStudentId());
        testSubmission.setStudentName(testSubmissionModel.getName());
        testSubmission.setStatus(SubmissionStatus.UNCHECKED);
        return new  ApiResponse<>(testSubmissionRepository.save(testSubmission), HttpStatus.CREATED);
    }

    @Override
    public ApiResponse<Boolean> addAnswers(SubmissionSubmitRequest submissionSubmitRequest) {

        TestSubmission testSubmission = findById(submissionSubmitRequest.getTestSubmissionId());
        if(testSubmission == null){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Submission not found");
        }
        if(!testSubmission.getStudentId().equals(submissionSubmitRequest.getStudentId())){
            return new ApiResponse<>(HttpStatus.FORBIDDEN, "This is not your Submission!");
        }
        if(testSubmission.getAnswerDetailList() != null){
            return new ApiResponse<>(HttpStatus.CONFLICT, "Answers already submitted!");
        }
        List<AnswerDetail> answerDetailList = new ArrayList<>();
            for (AnswerDetailModel model : submissionSubmitRequest.getAnswerDetailModelList()) {
                answerDetailList.add(answerDetailService.createAnswerDetail(model, submissionSubmitRequest.getTestSubmissionId()));
            }
            testSubmission.setAnswerDetailList(answerDetailList); // answers submitted
            testSubmission.setSubmittedAt(LocalDateTime.now()); // submission time record
            testSubmission.setStatus(submissionSubmitRequest.getStatus()); //
            testSubmissionRepository.save(testSubmission);// save submission
            return new ApiResponse<>(true,HttpStatus.OK);
    }

    @Override
    public ApiResponse<TestSubmission> evaluateSubmission(UpdateTestSubmission updateTestSubmission) {
        TestSubmission testSubmission = findById(updateTestSubmission.getId());
        if(testSubmission == null){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Submission not found");
        }
        if(testSubmission.getStatus() == SubmissionStatus.CHECKED){
            return new ApiResponse<>(HttpStatus.FORBIDDEN, "Submission is already reviewed!");
        }
        /// For some scenario I was not check terminate validation,
        /// if submission was terminated evaluator still check submission
        List<AnswerDetail> answerDetailList = new ArrayList<>();
        double total = 0;
            for (UpdateAnswerDetailModel model : updateTestSubmission.getAnswerDetailList()) {
                answerDetailList.add(answerDetailService.evaluateAnswer(model));
                total = total + model.getMarksObtained();
            }
            testSubmission.setAnswerDetailList(answerDetailList); // answers evaluated
            testSubmission.setEvaluatedAt(LocalDateTime.now()); // evaluate time record
            testSubmission.setTotalMarks(total); // total marks
            testSubmission.setFeedback(updateTestSubmission.getFeedback()); // feedback
            testSubmission.setStatus(SubmissionStatus.CHECKED); // status
            testSubmission.setEvaluatorId(updateTestSubmission.getEvaluatorId()); // evaluator Id
            return new ApiResponse<>(testSubmissionRepository.save(testSubmission), HttpStatus.OK);// save submission
    }

    @Override
    public ApiResponse<List<TestSubmission>> getSubmissionsOfTest(String testId) {
        return new ApiResponse<>(testSubmissionRepository.findAllByTestId(testId), HttpStatus.OK);
    }

    @Override
    public ApiResponse<TestSubmission> getSubmissionsOfTestByStudentId(String testId, String studentId) {
        TestSubmission submission = testSubmissionRepository.findByTestIdAndStudentId(testId, studentId);
        if(submission == null){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Submission Not Found");
        }
        return new ApiResponse<>(submission, HttpStatus.OK);
    }

    @Override
    public ApiResponse<ResultResponse> generateResult(String testId) {
        ClassTest test = Optional.ofNullable(classTestService.findById(testId)).orElseThrow(
                () -> new ApplicationException("Test not found "));
        List<TestSubmission> submissions = testSubmissionRepository.findAllByTestId(testId);

        return null;
    }

    @Override
    public TestSubmission findById(String testSubmissionId) {
        return testSubmissionRepository.findById(testSubmissionId).orElse(null);
    }
}