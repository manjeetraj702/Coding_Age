package com.educore.repository;

import com.educore.model.entity.Content;
import com.educore.model.enums.ContentCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends MongoRepository<Content, String> {

    List<Content> findAllByCategory(ContentCategory enumCategory);
}
