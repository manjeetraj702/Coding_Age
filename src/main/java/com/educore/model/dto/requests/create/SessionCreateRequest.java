package com.educore.model.dto.requests.create;

import com.educore.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class SessionCreateRequest {

    private String batchId;

    private String topic;

    private String creatorId;

    private String instructorName;

    private Role creatorRole;

    private LocalTime startTime;

    private LocalTime endTime;

//  private String sessionRecordingUrl;
    // Session Recording take while creating session and notes and practice sets later
    //because session recording is mandatory but practice sets and notes 'optional'
}
