package com.educore.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private String id;

    private String title;

    private String description;

    private String imageUrl;

    private int noOfEnquirers;

    private String createdBy;

    private boolean isActive;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String location;

}
