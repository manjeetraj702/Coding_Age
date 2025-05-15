package com.educore.model.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Address {
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String landmark;
}