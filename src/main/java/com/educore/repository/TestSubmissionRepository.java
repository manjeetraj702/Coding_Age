package com.educore.repository;

import com.educore.model.entity.TestSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestSubmissionRepository extends MongoRepository<TestSubmission, String> {

    List<TestSubmission> findAllByTestId(String testId);

    TestSubmission findByTestIdAndStudentId(String testId, String studentId);
}
