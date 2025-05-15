package com.educore.model.dto.response;

import com.educore.model.enums.ContentCategory;
import com.educore.model.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO {
    private String id;

    private String courseId;  // Course Id

    private String topic;     // For Topic-wise organization

    private ContentCategory category;

    private Level level;

    private String title;     // Title of Note (e.g., "Introduction to Java")

    private String description;  // Short description of the note

    private String contentUrl;    // URL of PDF  (Firebase, Supabase or AWS where it stores)

    private String thumbnailUrl;

    private boolean isPremium;  // Free and premium content control
}
