package com.educore.repository;

import com.educore.model.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SessionRepository extends MongoRepository<Session, String> {

    List<Session> findAllByBatchId(String batchId);

//    Page<Session> findAllByBatchId(String batchId, Pageable pageable);
}
