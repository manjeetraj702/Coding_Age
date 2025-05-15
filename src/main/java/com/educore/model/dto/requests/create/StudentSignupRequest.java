package com.educore.model.dto.requests.create;

import com.educore.model.entity.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentSignupRequest {
    private SignUpRequest signUpRequest;
    private String userId;
    private Address address;
    private String parentMobNo;
    private LocalDateTime enrollmentDate;

    public StudentSignupRequest(String userId, Address address, String parentMobNo, LocalDateTime enrollmentDate){
        this.userId = userId;
        this.address = address;
        this.parentMobNo = parentMobNo;
        this.enrollmentDate =  enrollmentDate;
    }

    public StudentSignupRequest(SignUpRequest signUpRequest, Address address, String parentMobNo, LocalDateTime enrollmentDate){
        this.signUpRequest = signUpRequest;
        this.address = address;
        this.parentMobNo = parentMobNo;
        this.enrollmentDate =  enrollmentDate;
    }
}
