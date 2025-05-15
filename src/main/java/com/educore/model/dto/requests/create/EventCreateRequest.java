package com.educore.model.dto.requests.create;

import com.educore.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class EventCreateRequest {

    private String title;

    private String description;

    private String imageUrl;

    private String createdBy;

    private Role creatorRole;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String location;
}
