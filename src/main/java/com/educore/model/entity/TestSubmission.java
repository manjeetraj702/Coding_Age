package com.educore.model.entity;

import com.educore.model.enums.SubmissionStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "test_submissions")
@NoArgsConstructor
@AllArgsConstructor
public class TestSubmission {
    @Id
    private String id;

    @Field(name = "test_id")
    private String testId;

    @Field(name = "student_id")
    private String studentId;

    private String studentName;

    @Field(name = "evaluator_id")
    private String evaluatorId;

    //TODO here add one more field evaluator Name

    @Field(name = "answer_detail_list")
    private List<AnswerDetail> answerDetailList;

    private String feedback;

    @Field(name = "total_marks")
    private double totalMarks;

    private SubmissionStatus status; // Checked, UnChecked, Terminated

    @Field(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Field(name = "evaluated_at")
    private LocalDateTime evaluatedAt;

    @Field(name = "updated_at")
    private LocalDateTime updatedAt;
}
