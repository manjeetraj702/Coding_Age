package com.educore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "subject")
public class Subject {
    @Id
    private String id;
    private String name;
    private Set<String> topics;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Subject(){
        this.topics = new HashSet<>();
    }
}
