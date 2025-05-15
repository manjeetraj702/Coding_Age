package com.educore.model.entity;

import com.educore.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "enrollments")
@CompoundIndex(def = "{'studentId': 1, 'courseId': 1}", unique = true)
public class Enrollment {
    @Id
    private String id;

    @Indexed
    private String studentId;

    @Indexed
    private String courseId; // currently course model is not created

    private LocalDate enrollmentDate;

    private String remarks;

    private LocalDate certificateIssuedDate;

    private Role enrolledBy; // ADMIN, EMPLOYEE, SELF

    private String sourceOfEnrollment;
}
