package com.educore.model.dto.requests.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class SubjectCreateRequest {
    private String name;
    private Set<String> topics;
}
