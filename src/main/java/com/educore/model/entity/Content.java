package com.educore.model.entity;

import com.educore.model.enums.ContentCategory;
import com.educore.model.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "contents")
public class Content {
    @Id
    private String id;

    @Indexed
    private String courseId;  // Course Id

    @Indexed
    private String topic;  // For Topic-wise organization

    @Indexed
    private ContentCategory category;

    @Indexed
    private Level level;

    private String title;  // Title of Note (e.g., "Introduction to Java")

    private String description;  // Short description of the note

    private String contentUrl;    // URL of PDF  (Firebase, Supabase or AWS where it stores)

    private String thumbnailUrl;

    private boolean isPremium;  // Free and premium content control

    private double price;

    private String createdBy;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;
}
