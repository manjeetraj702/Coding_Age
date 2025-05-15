package com.educore.model.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "events")
public class Event {
    @Id
    private String id;

    private String title;

    private String description;

    private String imageUrl;

    private int noOfEnquirers;

    private Set<String> enquirersId = new HashSet<>();

    private String createdBy;

    private boolean isActive;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String location;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

}