package com.educore.repository;

import com.educore.model.entity.ClassTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassTestRepository extends MongoRepository<ClassTest, String> {

    List<ClassTest> findAllByBatchId(String batchId, Sort sort);
}
