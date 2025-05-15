package com.educore.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentListResponse {
    private String id;
    private String name;
    private String mobNo;
    public StudentListResponse(String id, String name){
        this.id = id;
        this.name = name;
    }
}
