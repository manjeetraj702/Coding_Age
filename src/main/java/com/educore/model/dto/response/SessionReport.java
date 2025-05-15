package com.educore.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class SessionReport {
    private LocalDate sessionDate;
    private String instructorName;
    private String topic;
    private int noOfAbsent;
    private int noOfPresent;
}
