package com.educore.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "answer_details")
public class AnswerDetail {
    @Id
    private String id;
    @Field(name = "test_submission_id")
    private String testSubmissionId;
    private String question;
    private String answer;
    @Field(name = "marks_obtained")
    private double marksObtained;
    @Field(name = "is_correct")
    private boolean isCorrect;
    @Field(name = "evaluator_comment")
    private String evaluatorComment;
}
