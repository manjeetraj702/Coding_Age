package com.educore.model.entity;
import com.educore.model.enums.Status;
import com.educore.model.enums.Level;
import com.educore.model.enums.Mode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "courses")
public class Course {
    @Id
    private String id;

    @Indexed
    private String adminId;

    private String title;

    private String description;

    private String duration;

    private double price;

    private List<String> images; // Course images

    private Mode mode;

    private Level level; // Beginner, Intermediate, Advanced

    private List<String> topics; // Topics covered

    private List<String> recordings; // links of recordings e.g. (about course, tutorial video)

    private List<String> resources; // PDFs, Brochure, free notes

    private Set<String> batchIds; // Ids of Batches

    private LocalDateTime enrollmentStartDate;

    private LocalDateTime enrollmentEndDate;

    private LocalDate courseStartDate;

    private LocalDate courseEndDate;

    private boolean certificateAvailable;

    private int maxEnrollment;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course(){
        this.images = new ArrayList<>();
        this.topics = new ArrayList<>();
        this.recordings = new ArrayList<>();
        this.resources = new ArrayList<>();
        this.batchIds = new HashSet<>();
    }
}
