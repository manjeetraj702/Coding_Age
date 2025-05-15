package com.educore.model.dto.requests.create;

import com.educore.model.enums.ContentCategory;
import com.educore.model.enums.Level;
import com.educore.model.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContentCreateRequest {
    private String courseId;

    private String topic;

    private ContentCategory category;

    private Level level;

    private String title;

    private String description;

    private String contentUrl;

    private boolean isPremium;

    private String createdBy;

    private Role creatorRole;

}
