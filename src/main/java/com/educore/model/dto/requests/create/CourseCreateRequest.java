package com.educore.model.dto.requests.create;

import com.educore.model.enums.Category;
import com.educore.model.enums.Level;
import com.educore.model.enums.Mode;
import com.educore.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CourseCreateRequest {

    private String adminId;

    private String title;

    private String description;

    private double price;

    private List<String> images; // Course images

    private Mode mode;

    private Level level; // Beginner, Intermediate, Advanced

    private List<String> topics; // Topics covered

    private LocalDateTime enrollmentStartDate;

    private LocalDateTime enrollmentEndDate;

    private LocalDate courseStartDate;

    private LocalDate courseEndDate;

    private boolean certificateAvailable;

    private int maxEnrollment;

    private Status status;
}
